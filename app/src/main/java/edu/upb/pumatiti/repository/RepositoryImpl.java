package edu.upb.pumatiti.repository;

import androidx.lifecycle.LiveData;

import edu.upb.pumatiti.models.repository.Base;

public interface RepositoryImpl {
    LiveData<Base> login(String email, String password);

    LiveData<Base> getRoutes();

    LiveData<Base> getRouteDetails(String uuid);
}
