package com.johnnymolina.workoutswithimgur.interfaces;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by Johnny Molina on 7/19/2015.
 */
public interface MainActivityView extends MvpView {

//    void setData(List<MovieInfo> list);
//
//    void setRealmData(List<RealmReturnedMovie> realmList);

    void showLoading();

    void showError(Throwable e);

    void showView();


}