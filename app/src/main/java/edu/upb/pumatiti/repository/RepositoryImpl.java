package edu.upb.pumatiti.repository;

import androidx.lifecycle.LiveData;

import edu.upb.pumatiti.models.repository.Base;
import edu.upb.pumatiti.models.repository.User;

public interface RepositoryImpl {
    LiveData<Base> login(String email, String password);

    LiveData<Base> getRoutes();

    LiveData<Base> getRouteDetails(String uuid);
}
