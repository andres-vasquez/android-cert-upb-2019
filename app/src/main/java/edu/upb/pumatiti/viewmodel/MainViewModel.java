package edu.upb.pumatiti.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.upb.pumatiti.models.repository.Base;
import edu.upb.pumatiti.models.repository.Route;
import edu.upb.pumatiti.repository.Repository;

public class MainViewModel extends AndroidViewModel {
    Repository repository;

    private LiveData<Base> allRoutes;

    public MainViewModel(Application application) {
        super(application);
        repository = new Repository(application);
        allRoutes = repository.getRoutes();
    }

    public LiveData<Base> getRouteDetails(String uuid) {
        return repository.getRouteDetails(uuid);
    }
}
