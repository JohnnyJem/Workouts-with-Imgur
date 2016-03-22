package com.johnnymolina.workoutswithimgur.views;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.johnnymolina.workoutswithimgur.AppComponent;
import com.johnnymolina.workoutswithimgur.ImgurApplication;
import com.johnnymolina.workoutswithimgur.other.RxBus;

import javax.inject.Inject;

import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Johnny on 3/20/2016.
 */
public class MainActivityPresenter extends MvpBasePresenter<MainActivityView> {


    @Inject ImgurApplication imgurApplication;
    @Inject RxBus rxBus;

    private CompositeSubscription subscriptions;

    //constructor that will be passed an appComponent to init Dependency Injection
    public MainActivityPresenter(AppComponent appComponent){
        injectDependencies(appComponent);
        initRxBus();
    }

    private void initRxBus() {
        subscriptions = new CompositeSubscription();
        subscriptions
                .add(rxBus.toObserverable()//
                       .subscribe(new Action1<Object>() {
                            @Override
                            public void call(Object event) {
                                //Do something with the object passed through the event bus.
                                // If many objects use switch.
                            }
                        }));
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
        //getView().setAlbum();
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