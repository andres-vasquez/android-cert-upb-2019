package edu.upb.pumatiti.preparation.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import edu.upb.pumatiti.preparation.models.repository.Base;
import edu.upb.pumatiti.preparation.models.repository.Bus;
import edu.upb.pumatiti.preparation.models.repository.Route;
import edu.upb.pumatiti.preparation.repository.api.ApiRepository;
import edu.upb.pumatiti.preparation.repository.firebase.FirebaseRepository;
import edu.upb.pumatiti.preparation.utils.Constants;

public class Repository implements RepositoryImpl {

    private static final String LOG = Repository.class.getSimpleName();

    public Repository(Application application) {

    }

    @Override
    public LiveData<Base> login(String email, String password) {
        return FirebaseRepository.getInstance().login(email, password);
    }

    @Override
    public LiveData<Base> getRoutes() {
        return ApiRepository.getInstance().getRoutes();
    }

    @Override
    public LiveData<Base> getRouteDetails(String routeId) {
        return FirebaseRepository.getInstance().subscribeToValues("buses");
    }

    @Override
    public void saveGpsPosition(String path, Bus bus) {
        FirebaseRepository.getInstance().setValue(path, bus);
    }
}
