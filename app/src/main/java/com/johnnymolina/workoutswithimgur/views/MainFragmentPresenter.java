package com.johnnymolina.workoutswithimgur.views;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.johnnymolina.workoutswithimgur.AppComponent;
import com.johnnymolina.workoutswithimgur.ImgurApplication;
import com.johnnymolina.workoutswithimgur.other.RxBus;

import javax.inject.Inject;

/**
 * Created by Johnny on 3/19/2016.
 */
public class MainFragmentPresenter extends MvpBasePresenter<MainFragmentView> {

    @Inject ImgurApplication imgurApplication;
    @Inject RxBus rxBus;

    //constructor that will be passed an appComponent to init Dependency Injection
    public MainFragmentPresenter(AppComponent appComponent){
        injectDependencies(appComponent);
    }


    public void loadData(boolean var1){
        //getView().showLoading;
    }

    public void loadRealmData(){
        //getView().showLoading;
    }

    public void refreshData(boolean var1){
        //getView().showLoading;
    }

    void setData(){
        //getView().setData();
    }

    void handleError(Throwable var1, boolean var2){
        //getView().showError();
    }

    void showContent(){
        //getView().showContent();
    }

    public void injectDependencies(AppComponent appComponent){
        appComponent.inject(this);
    }

}
