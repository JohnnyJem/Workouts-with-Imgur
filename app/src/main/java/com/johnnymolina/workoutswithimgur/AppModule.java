package com.johnnymolina.workoutswithimgur;

import android.content.Context;
import android.content.SharedPreferences;

import com.johnnymolina.workoutswithimgur.other.RxBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by Johnny Molina on 7/19/2015.
 */
//This is a module - it contains methods that provide dependencies

@Module
public class AppModule {
    private final String APPLICATION_TAG = "com.johnnymolina.workoutswithimgur";

    private ImgurApplication imgurApplication;
    private RxBus _rxBus = null;
    private Realm realm;
    private SharedPreferences sharedPreferences;


    public AppModule(ImgurApplication imgurApplication){
        this.imgurApplication = imgurApplication;
    }

    //Providing ApplicationContext to enable references that survive
    //during the lifetime of the application
    @Provides @Singleton
    public ImgurApplication provideMovieApplication(){
        return this.imgurApplication;
    }

    @Provides @Singleton
    public RxBus provideRxBus() {
        if (_rxBus == null) {
            _rxBus = new RxBus();
        }
        return _rxBus;
    }


    @Provides @Singleton
    public SharedPreferences provideSharedPreferences (ImgurApplication imgurApplication){
        if (sharedPreferences == null){
           sharedPreferences = imgurApplication.getSharedPreferences(APPLICATION_TAG, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    @Provides @Singleton
    public Realm provideRealm (ImgurApplication imgurApplication){
        if (realm == null){
            realm = Realm.getInstance(imgurApplication);
        }
        return realm;
    }

}