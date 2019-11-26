package edu.upb.pumatiti.repository;

import androidx.lifecycle.LiveData;

import edu.upb.pumatiti.models.repository.Base;
import edu.upb.pumatiti.repository.api.ApiRepository;
import edu.upb.pumatiti.repository.firebase.FirebaseRepository;

public class Repository implements RepositoryImpl {

    @Override
    public LiveData<Base> login(String email, String password) {
        return FirebaseRepository.getInstance().login(email, password);
    }

    @Override
    public LiveData<Base> getRoutes() {
        return ApiRepository.getInstance().getRoutes();
    }

    @Override
    public LiveData<Base> getRouteDetails(String uuid) {
        return null;
    }
}
