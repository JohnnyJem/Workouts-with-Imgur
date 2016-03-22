package com.johnnymolina.workoutswithimgur;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.johnnymolina.workoutswithimgur.other.ReleaseTree;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

/**
 * Created by Johnny on 3/7/2016.
 */

public class ImgurApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree(){
                //Add the line number to the tag.
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element) + ':' + element.getLineNumber();
                }
            });
            LeakCanary.install(this);
        }else{
            //release mode
            Timber.plant(new ReleaseTree());
        }
        // Create an InitializerBuilder
        Stetho.InitializerBuilder initializerBuilder =
                Stetho.newInitializerBuilder(this);

        // Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
        );

        // Enable command line interface
        initializerBuilder.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this)
        );

        // Use the InitializerBuilder to generate an Initializer
        Stetho.Initializer initializer = initializerBuilder.build();

        // Initialize Stetho with the Initializer
        Stetho.initialize(initializer);

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
