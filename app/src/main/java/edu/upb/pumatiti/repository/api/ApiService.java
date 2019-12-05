package edu.upb.pumatiti.repository.api;

import edu.upb.pumatiti.utils.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.API_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
