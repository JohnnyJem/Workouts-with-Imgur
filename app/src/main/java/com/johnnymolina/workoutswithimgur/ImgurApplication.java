package com.johnnymolina.workoutswithimgur;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Johnny on 3/7/2016.
 */

public class ImgurApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        initializeInjector();
    }


    private void initializeInjector() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                //.networkModule(new NetworkModule())
                .build();
        appComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
