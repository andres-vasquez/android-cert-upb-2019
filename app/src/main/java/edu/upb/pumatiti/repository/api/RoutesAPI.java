package edu.upb.pumatiti.repository.api;

import java.util.List;

import edu.upb.pumatiti.models.repository.Route;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RoutesAPI {
    @GET("api%2Froutes.json")
    Call<List<Route>> getRoutes(@Query("alt") String alt);

    //E.g. Create a route
    @POST("routes")
    Call<List<Route>> createRoute(@Query("alt") String alt,
                                @Header("Accept") String accept,
                                @Body Object body);

    //E.g. Get a route by uuid
    @GET("routes/{routeUuid}")
    Call<Route> getRouteByUuid(@Path("routeUuid") String routedId,
                          @Query("alt") String alt,
                          @Header("Accept") String accept);
}
