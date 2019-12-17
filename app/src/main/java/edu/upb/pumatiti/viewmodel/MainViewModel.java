package edu.upb.pumatiti.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.nio.file.Path;
import java.util.List;

import edu.upb.pumatiti.models.repository.Base;
import edu.upb.pumatiti.models.repository.Route;
import edu.upb.pumatiti.models.ui.StopMarker;
import edu.upb.pumatiti.repository.MockRepository;
import edu.upb.pumatiti.repository.Repository;
import edu.upb.pumatiti.repository.RepositoryImpl;
import edu.upb.pumatiti.utils.ResponseMapper;

public class MainViewModel extends AndroidViewModel {

    private RepositoryImpl repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance();
        //repository = MockRepository.getInstance();
    }

    public LiveData<Base> getRoutes() {
        final MutableLiveData<Base> results = new MutableLiveData<>();
        repository.getRoutes().observeForever(new Observer<Base>() {
            @Override
            public void onChanged(Base base) {
                if (base.isSuccess()) {
                    List<Route> routeList = (List<Route>) base.getData();
                    List<StopMarker> stopMarkers = ResponseMapper.mapRoutesToStopMarker(routeList);
                    results.postValue(new Base(stopMarkers));
                } else {
                    results.postValue(base);
                }
            }
        });
        return results;
    }
}
