package edu.upb.pumatiti.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import edu.upb.pumatiti.models.repository.Base;
import edu.upb.pumatiti.models.repository.Route;
import edu.upb.pumatiti.models.repository.User;

public class Repository implements RepositoryImpl {

    public Repository(Application application) {

    }

    @Override
    public LiveData<Base> login(String email, String password) {
        MutableLiveData<Base> userMutableLiveData = new MutableLiveData<>();
        User user = new User();
        user.setName("Test");
        user.setEmail("test@test.com");

        userMutableLiveData.postValue(new Base(user));
        return userMutableLiveData;
    }

    @Override
    public LiveData<Base> getRoutes() {
        MutableLiveData<Base> routesMutableLiveData = new MutableLiveData<>();
        List<Route> routes = new ArrayList<>();
        //Routes without bus data

        routes.add(new Route("1", "Chasquipampa"));
        routes.add(new Route("2", "Irpavi"));

        routesMutableLiveData.postValue(new Base(routes));
        return routesMutableLiveData;
    }

    @Override
    public LiveData<Base> getRouteDetails(String uuid) {
        MutableLiveData<Base> routeMutableLiveData = new MutableLiveData<>();
        //Single route with route data
        return routeMutableLiveData;
    }
}
