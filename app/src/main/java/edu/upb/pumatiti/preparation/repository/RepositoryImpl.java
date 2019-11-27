package edu.upb.pumatiti.preparation.repository;

import androidx.lifecycle.LiveData;

import edu.upb.pumatiti.preparation.models.repository.Base;
import edu.upb.pumatiti.preparation.models.repository.Bus;

public interface RepositoryImpl {
    LiveData<Base> login(String email, String password);

    LiveData<Base> getRoutes();

    LiveData<Base> getRouteDetails(String uuid);

    void saveGpsPosition(String path, Bus bus);
}
