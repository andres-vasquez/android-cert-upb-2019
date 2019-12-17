package edu.upb.pumatiti.preparation.viewmodel;

import android.app.Application;
import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.upb.pumatiti.preparation.models.repository.Base;
import edu.upb.pumatiti.preparation.models.repository.Bus;
import edu.upb.pumatiti.preparation.models.repository.Route;
import edu.upb.pumatiti.preparation.models.ui.BusMarker;
import edu.upb.pumatiti.preparation.models.ui.StopMarker;
import edu.upb.pumatiti.preparation.repository.Repository;
import edu.upb.pumatiti.preparation.utils.Constants;
import edu.upb.pumatiti.preparation.utils.ResponseMapper;

public class MainViewModel extends AndroidViewModel {
    private static final String LOG = MainViewModel.class.getSimpleName();
    Repository repository;

    private List<Route> routes = new ArrayList<>();

    private MutableLiveData<Bus> myBus = new MutableLiveData<>();
    private MutableLiveData<List<BusMarker>> buses = new MutableLiveData<>();
    private MutableLiveData<List<StopMarker>> stops = new MutableLiveData<>();

    private double lat = Constants.DEFAULT_LAT;
    private double lng = Constants.DEFAULT_LNG;

    private String busUuid;
    private boolean started = false;
    private Handler handler = new Handler();

    private Runnable runnable = () -> {
        saveCoordinates(busUuid);
        if (started) {
            startTimer();
        }
    };


    public MainViewModel(Application application) {
        super(application);
        repository = new Repository(application);
        repository.getRoutes().observeForever(base -> {
            if (base.isSuccess()) {
                routes = (List<Route>) base.getData();
                Log.e("Routes", new Gson().toJson(routes));
                stops.postValue(ResponseMapper.mapRoutesToStopMarker(routes));
                myBus.postValue(ResponseMapper.findMyBus(routes, busUuid));
                getRouteDetails("route-01");
            }
        });
    }

    public void getRouteDetails(String uuid) {
        repository.getRouteDetails(uuid).observeForever(base -> {
            if (base.isSuccess()) {
                List<Bus> busList = (List<Bus>) base.getData();
                buses.postValue(ResponseMapper.mapBusesToBusMarker(busList));
            }
        });
    }

    public void getMyBus(String busUuid) {
        this.busUuid = busUuid;
    }

    public LiveData<List<BusMarker>> subscribeToBuses() {
        return buses;
    }

    public LiveData<List<StopMarker>> subscribeToStops() {
        return stops;
    }

    public LiveData<Bus> subscribeToMyBus() {
        return myBus;
    }

    public void startTimer() {
        started = true;
        handler.postDelayed(runnable, Constants.GPS_TIMER);
    }

    public void stopTimer() {
        started = false;
        handler.removeCallbacks(runnable);
    }

    private void saveCoordinates(String busUuid) {
        Log.e(LOG, "PC1: " + lat + "," + lng);
        lat += getRandom();
        lng += getRandom();
        Log.e(LOG, "PC2: " + lat + "," + lng);


        if (myBus.getValue() != null) {
            Bus bus = myBus.getValue();
            bus.setLat(lat);
            bus.setLng(lng);

            String path = Constants.FIREBASE_PATH_BUSES + "/" + busUuid;
            repository.saveGpsPosition(path, bus);

            List<Bus> busesList = new ArrayList<>();
            busesList.add(bus);
            buses.postValue(ResponseMapper.mapBusesToBusMarker(busesList));
        }
    }

    private double getRandom() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(100);
        return randomInt / 999999.9;
    }
}
