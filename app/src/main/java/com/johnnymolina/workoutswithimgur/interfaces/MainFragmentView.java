package com.johnnymolina.workoutswithimgur.interfaces;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by Johnny on 3/18/2016.
 */
public interface MainFragmentView extends MvpView {

    void setData();

    void showLoading();

    void showError(Throwable e);

    void showView();


}
