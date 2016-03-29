package com.johnnymolina.workoutswithimgur.views;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.johnnymolina.workoutswithimgur.network.api.model.Album;

/**
 * Created by Johnny on 3/28/2016.
 */
public interface DownloadFragmentView extends MvpView {

    void loadData(String query);

    void refreshData(boolean var1);

    void showLoading(boolean var1);

    void setData(Album var1);

    void showError(Throwable e);

    void showSuccess(String message,boolean isVisible);

    void showContent();

}

