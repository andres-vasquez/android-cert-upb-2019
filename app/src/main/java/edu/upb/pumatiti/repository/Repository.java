package edu.upb.pumatiti.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import edu.upb.pumatiti.models.repository.Base;
import edu.upb.pumatiti.models.repository.Route;
import edu.upb.pumatiti.models.repository.User;
import edu.upb.pumatiti.repository.firebase.FirebaseRepository;
import edu.upb.pumatiti.utils.Constants;

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
        MutableLiveData<Base> routesMutableLiveData = new MutableLiveData<>();
        try {
            List<Route> routes = new Gson().fromJson(Constants.TEMP_ROUTES, new TypeToken<List<Route>>() {
            }.getType());
            routesMutableLiveData.postValue(new Base(routes));
        } catch (Exception ex) {
            Log.e(LOG, "" + ex.getMessage());
        }
        return routesMutableLiveData;
    }

    @Override
    public LiveData<Base> getRouteDetails(String uuid) {
        MutableLiveData<Base> routeMutableLiveData = new MutableLiveData<>();
        //Single route with route data
        return routeMutableLiveData;
    }
}
