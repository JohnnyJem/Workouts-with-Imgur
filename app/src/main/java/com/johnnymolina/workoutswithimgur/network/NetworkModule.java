package com.johnnymolina.workoutswithimgur.network;

import com.johnnymolina.workoutswithimgur.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Static factory methods for creating {@link Endpoint} instances.
 *
 * Created by Johnny Molina on 7/19/2015.
 */
//Todo: Fix this class to enable retrofit 2 support.
@Module
public class NetworkModule {
//    public static String API_KEY= BuildConfig.MY_IMGUR_API_KEY; //see readme
//    public static String HTTP_REQUEST_DISCOVER="http://api.themoviedb.org";
//    public static final String PRODUCTION_API_URL = "http://image.tmdb.org/t/p/w185//";
//    public static String BASE_URL="http://image.tmdb.org/t/p/";
//
//
//    @Provides
//    @Singleton
//    OkHttpClient provideOkHttpClient() {
//        return new OkHttpClient();
//    }
//
//
//    @Provides
//    @Singleton
//    Endpoint provideEndpoint() {
//        return Endpoints.newFixedEndpoint(HTTP_REQUEST_DISCOVER);
//    }
//
//    @Provides
//    @Singleton
//    RequestInterceptor provideRequestInterceptor(){
//        return  new RequestInterceptor() {
//            @Override
//            public void intercept(RequestFacade request) {
//                request.addHeader("Accept", "application/json");
//            }
//        };
//    }
//
//    @Provides
//    @Singleton
//    Gson provideGson() {
//        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
//    }
//
//
//    @Provides
//    @Singleton
//    RestAdapter provideRestAdapter(Endpoint endpoint,
//                                   OkHttpClient client,
//                                   Gson gson,RequestInterceptor requestInterceptor) {
//        return new RestAdapter.Builder()
//                .setClient(new OkClient(client))
//                .setEndpoint(endpoint)
//                .setRequestInterceptor(requestInterceptor)
//                .setLogLevel(RestAdapter.LogLevel.FULL)
//                .setConverter(new GsonConverter(gson))
//                .build();
//    }
//
//    @Provides
//    @Singleton
//    ImgurService provideMovieService(RestAdapter restAdapter) {
//        return restAdapter.create(ImgurService.class);
//    }


}