package edu.upb.pumatiti.preparation.repository.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import edu.upb.pumatiti.preparation.models.repository.Base;
import edu.upb.pumatiti.preparation.models.repository.Route;
import edu.upb.pumatiti.preparation.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepository {
    private static final String LOG = ApiRepository.class.getSimpleName();

    private RoutesAPI routesAPI;
    private static ApiRepository instance;

    public static ApiRepository getInstance() {
        if (instance == null) {
            instance = new ApiRepository();
        }
        return instance;
    }

    public ApiRepository() {
        routesAPI = ApiService.createService(RoutesAPI.class);
    }

    public LiveData<Base> getRoutes() {
        final MutableLiveData<Base> result = new MutableLiveData<>();
        routesAPI.getRoutes(Constants.API_PARAM_ALT, Constants.API_PARAM_TOKEN).enqueue(new Callback<List<Route>>() {
            @Override
            public void onResponse(Call<List<Route>> call, Response<List<Route>> response) {
                if (response.isSuccessful()) {
                    result.postValue(new Base(response.body()));
                } else {
                    result.postValue(new Base(response.message(), new NullPointerException()));
                }
            }

            @Override
            public void onFailure(Call<List<Route>> call, Throwable t) {
                result.postValue(new Base(t.getMessage(), new Exception(t)));
            }
        });
        return result;
    }
}
