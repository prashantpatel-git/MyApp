package com.prashant.RoomDemo.retrofit;

import com.prashant.RoomDemo.models.NearbyPlacesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Prashant on 11/03/18.
 */

public interface RetrofitApiInterface {

    @GET("place/nearbysearch/json")
    Observable<NearbyPlacesResponse> getNearbyPlaces(
            @Query("location") String location,
            @Query("type") String placeType,
            @Query("radius") int radius,
            @Query("key") String apiKey);
}
