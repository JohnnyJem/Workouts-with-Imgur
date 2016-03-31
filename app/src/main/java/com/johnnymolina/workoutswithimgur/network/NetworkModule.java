package com.johnnymolina.workoutswithimgur.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.johnnymolina.workoutswithimgur.BuildConfig;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 *
 * Created by Johnny Molina on 7/19/2015.
 */
//Todo: Fix this class to enable retrofit 2 support.
@Module
public class NetworkModule {
    public static String API_KEY = BuildConfig.MY_IMGUR_API_KEY; //see readme
    public static final String PRODUCTION_API_URL = "https://api.imgur.com/3/";
    public static String BASE_URL ="https://api.imgur.com";

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @Singleton
    Interceptor provideInterceptor(){
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Authorization","Client-ID " + BuildConfig.MY_IMGUR_API_KEY)
                        .build();
                return chain.proceed(newRequest);
            }
        };
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override public void log(String message) {
                Timber.tag("OkHttp").d(message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }


    @Provides
    @Singleton
    StethoInterceptor provideStethoInterceptor(){
        StethoInterceptor stethoInterceptor = new StethoInterceptor();
        return stethoInterceptor;
    }


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(
            Interceptor Interceptor,
            HttpLoggingInterceptor httpLoggingInterceptor,
            StethoInterceptor stethoInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.interceptors().add(Interceptor);
        builder.interceptors().add(httpLoggingInterceptor);
        builder.addNetworkInterceptor(stethoInterceptor);
        OkHttpClient client = builder.build();
        return client;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(PRODUCTION_API_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    ImgurService provideImgurService(Retrofit retrofit) {
        return retrofit.create(ImgurService.class);
    }


}
