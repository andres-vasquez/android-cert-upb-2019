package edu.upb.pumatiti.preparation.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import edu.upb.pumatiti.preparation.models.repository.Base;
import edu.upb.pumatiti.preparation.models.repository.Bus;
import edu.upb.pumatiti.preparation.models.repository.Route;
import edu.upb.pumatiti.preparation.repository.api.ApiRepository;
import edu.upb.pumatiti.preparation.repository.firebase.FirebaseRepository;
import edu.upb.pumatiti.preparation.repository.local.LocalRepository;
import edu.upb.pumatiti.preparation.utils.Constants;

public class Repository implements RepositoryImpl {

    private LocalRepository local;
    private static final String LOG = Repository.class.getSimpleName();

    public Repository(Application application) {
        local = new LocalRepository(application);
    }

    @Override
    public LiveData<Base> login(String email, String password) {
        return FirebaseRepository.getInstance().login(email, password);
    }

    @Override
    public LiveData<Base> getRoutes() {
        final MutableLiveData<Base> results = new MutableLiveData<>();
        local.getRoutes().observeForever(new Observer<List<Route>>() {
            @Override
            public void onChanged(List<Route> routes) {
                Log.e(LOG, new Gson().toJson(routes));
            }
        });

        ApiRepository.getInstance().getRoutes().observeForever(new Observer<Base>() {
            @Override
            public void onChanged(Base base) {
                if (base.isSuccess()) {
                    results.postValue(base);

                    List<Route> routes = (List<Route>) base.getData();
                    if (routes != null && !routes.isEmpty()) {
                        for (Route route : routes) {
                            local.insertRoute(route);
                        }
                    }
                } else {
                    local.getRoutes().observeForever(new Observer<List<Route>>() {
                        @Override
                        public void onChanged(List<Route> routes) {
                            results.postValue(new Base(routes));
                        }
                    });
                }
            }
        });
        return results;
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
