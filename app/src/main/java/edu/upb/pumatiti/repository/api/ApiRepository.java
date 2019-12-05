package edu.upb.pumatiti.repository.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import edu.upb.pumatiti.models.repository.Base;
import edu.upb.pumatiti.models.repository.Route;
import edu.upb.pumatiti.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepository {

    private RoutesAPI routesAPI;
    private static ApiRepository instance;

    public static ApiRepository getInstance() {
        if (instance == null) {
            instance = new ApiRepository();
        }
        return instance;
    }

    private ApiRepository() {
        routesAPI = ApiService.createService(RoutesAPI.class);
    }

    public LiveData<Base> getRoutes() {
        final MutableLiveData<Base> results = new MutableLiveData<>();

        routesAPI.getRoutes(Constants.API_PARAM_ALT)
                .enqueue(new Callback<List<Route>>() {
                    @Override
                    public void onResponse(Call<List<Route>> call, Response<List<Route>> response) {
                        if (response.isSuccessful()) {
                            results.postValue(new Base(response.body()));
                        } else {
                            results.postValue(new Base(response.message(), new NullPointerException()));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Route>> call, Throwable t) {
                        results.postValue(new Base("onFailure", new Exception(t)));
                    }
                });

        return results;
    }
}
