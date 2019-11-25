package edu.upb.pumatiti.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import edu.upb.pumatiti.models.repository.Base;
import edu.upb.pumatiti.models.repository.Route;
import edu.upb.pumatiti.models.ui.BusMarker;
import edu.upb.pumatiti.models.ui.StopMarker;
import edu.upb.pumatiti.repository.Repository;

public class MainViewModel extends AndroidViewModel {
    Repository repository;

    private LiveData<Base> allRoutes;
    private LiveData<List<BusMarker>> buses = new MutableLiveData<>();
    private LiveData<List<StopMarker>> stops = new MutableLiveData<>();


    public MainViewModel(Application application) {
        super(application);
        repository = new Repository(application);
        allRoutes = repository.getRoutes();
        simulateData();
    }

    public LiveData<Base> getRouteDetails(String uuid) {
        return repository.getRouteDetails(uuid);
    }

    public LiveData<List<BusMarker>> subscribeToBuses() {
        return buses;
    }

    public LiveData<List<StopMarker>> subscribeToStops() {
        return stops;
    }

    private void simulateData() {
    }
}
