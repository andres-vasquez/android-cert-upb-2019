package edu.upb.pumatiti.preparation.repository.api;


import java.util.List;

import edu.upb.pumatiti.preparation.models.repository.Route;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RoutesAPI {
    @GET("api%2Froutes.json")
    Call<List<Route>> getRoutes(@Query("alt") String alt, @Query("token") String token);
}
