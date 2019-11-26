package edu.upb.pumatiti.repository.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import edu.upb.pumatiti.models.repository.Base;

public class ApiRepository {
    private static ApiRepository instance;

    public static ApiRepository getInstance() {
        if (instance == null) {
            instance = new ApiRepository();
        }
        return instance;
    }

    private ApiRepository() {
    }

    public LiveData<Base> getRoutes() {
        MutableLiveData<Base> results = new MutableLiveData<>();
        //TODO magic
        return results;
    }
}
