package com.johnnymolina.workoutswithimgur;

import com.johnnymolina.workoutswithimgur.other.RxBus;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Johnny Molina on 7/19/2015.
 * This is a component that bridges modules and injections.
 */
@Singleton
@Component(
        modules = {
                AppModule.class,
                //NetworkModule.class
        }

)

public interface AppComponent {

    //void inject(ActivityMain activity);
    //void inject(SearchFragment fragment);
    //void inject(DetailsFrag fragment);
    void inject(ImgurApplication imgurApplication);




    ImgurApplication imgurApplication();

    RxBus rxBus();

    //OkHttpClient okHttpClient();

    //Gson gson();

    //RestAdapter restAdapter();

   // ImgurService imgurService();


    //public ReposAdapter adapter();
}