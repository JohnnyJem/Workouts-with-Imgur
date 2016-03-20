package com.johnnymolina.workoutswithimgur.views;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by Johnny on 3/18/2016.
 */
public interface MainFragmentView extends MvpView {

    void loadData(boolean var1);

    void showLoading(boolean var1);

    void setData();

    void showError(Throwable var1, boolean var2);

    void showContent();

}
