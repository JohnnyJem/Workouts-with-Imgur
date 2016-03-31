package com.johnnymolina.workoutswithimgur;

import com.johnnymolina.workoutswithimgur.network.ImgurService;
import com.johnnymolina.workoutswithimgur.network.NetworkModule;
import com.johnnymolina.workoutswithimgur.other.RxBus;
import com.johnnymolina.workoutswithimgur.views.DetailsFragment;
import com.johnnymolina.workoutswithimgur.views.DownloadFragment;
import com.johnnymolina.workoutswithimgur.views.DownloadFragmentPresenter;
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
                NetworkModule.class
        }

)

public interface AppComponent {

    void inject(ImgurApplication imgurApplication);

    void inject(MainActivity activity);
    void inject(MainActivityPresenter presenter);

    void inject(MainFragment fragment);
    void inject(MainFragmentPresenter presenter);

    void inject(DownloadFragment fragment);
    void inject(DownloadFragmentPresenter presenter);

    void inject(DetailsFragment fragment);


    ImgurApplication imgurApplication();

    RxBus rxBus();

    ImgurService imgurService();
}