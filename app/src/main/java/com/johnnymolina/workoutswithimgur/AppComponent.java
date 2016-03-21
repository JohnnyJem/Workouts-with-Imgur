package com.johnnymolina.workoutswithimgur;

import com.johnnymolina.workoutswithimgur.other.RxBus;
import com.johnnymolina.workoutswithimgur.views.DetailsFragment;
import com.johnnymolina.workoutswithimgur.views.MainActivity;
import com.johnnymolina.workoutswithimgur.views.MainActivityPresenter;
import com.johnnymolina.workoutswithimgur.views.MainFragment;
import com.johnnymolina.workoutswithimgur.views.MainFragmentPresenter;

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

    void inject(ImgurApplication imgurApplication);

    void inject(MainActivity activity);
    void inject(MainActivityPresenter presenter);

    void inject(MainFragment fragment);
    void inject(MainFragmentPresenter presenter);

    void inject(DetailsFragment fragment);


    RxBus rxBus();
    ImgurApplication imgurApplication();


    //OkHttpClient okHttpClient();

    //Gson gson();

    //RestAdapter restAdapter();

   // ImgurService imgurService();

    //public ReposAdapter adapter();
}