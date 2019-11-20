package edu.upb.pumatiti.repository;

import androidx.lifecycle.LiveData;

import edu.upb.pumatiti.models.repository.Base;
import edu.upb.pumatiti.models.repository.User;

public interface RepositoryImpl {
    Base login(String email, String password);
    //LiveData<User> login(String email, String password);

    Base getRoutes();

    Base getRouteDetails(String uuid);
}
