package com.johnnymolina.workoutswithimgur.views;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.johnnymolina.workoutswithimgur.network.api.model.Album;

/**
 * Created by Johnny on 3/18/2016.
 */
public interface MainFragmentView extends MvpView {

    void loadData(boolean var1);

    void refreshData(boolean var1);

    void showLoading(boolean var1);

    void setData(Album var1);

    void showError(Throwable e);

    void showContent();

}
