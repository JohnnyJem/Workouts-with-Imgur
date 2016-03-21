package com.johnnymolina.workoutswithimgur.views;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by Johnny Molina on 7/19/2015.
 */
public interface MainActivityView extends MvpView {

    void loadData(boolean var1);

    void showLoading(boolean var1);

    void setData();

    void showError(Throwable var1, boolean var2);

    void showContent();



}