package com.johnnymolina.workoutswithimgur.network;

import com.johnnymolina.workoutswithimgur.network.api.model.Album;
import com.johnnymolina.workoutswithimgur.network.api.model.Data;
import com.johnnymolina.workoutswithimgur.network.api.model.Image;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Johnny Molina on 7/19/2015.
 * This interface is a Retrofit endpoint
 */
public interface ImgurService {

        @GET("album/{id}")
        Observable<Data> dataRequest(
            @Path("id") String query);

        @GET("image/{id}")
        Observable<Album> albumRequest(
            @Path("id") String query,
            @Query("api_key") String apiKey);

        @GET("image/")
        Observable<Image> imageRequest(
                @Path("id") String query,
                @Query("api_key") String apiKey);

}