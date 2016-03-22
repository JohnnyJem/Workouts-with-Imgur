package com.johnnymolina.workoutswithimgur.views;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.johnnymolina.workoutswithimgur.AppComponent;
import com.johnnymolina.workoutswithimgur.ImgurApplication;
import com.johnnymolina.workoutswithimgur.network.ImgurService;
import com.johnnymolina.workoutswithimgur.network.api.model.Data;
import com.johnnymolina.workoutswithimgur.other.RxBus;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Johnny on 3/19/2016.
 */
public class MainFragmentPresenter extends MvpBasePresenter<MainFragmentView> {

    @Inject ImgurApplication imgurApplication;
    @Inject RxBus rxBus;
    @Inject ImgurService imgurService;

    CompositeSubscription compositeSubscription;
    List<Data> dataList;

    String currentQuery;


    //constructor that will be passed an appComponent to init Dependency Injection
    public MainFragmentPresenter(AppComponent appComponent){
        injectDependencies(appComponent);
    }


    public void loadData(String query){
//        if (this.currentQuery != query) {
//            currentQuery = query;
//        }
            //update View
            if (isViewAttached()) {
                getView().showLoading(true);//grabbing the view reference
            }
            //we ask the presenter to perform a search with a query
            imgurService.dataRequest(query) //subscribes to the Observable provided by Retrofit and lets the View know what to display
                    .delay(5, TimeUnit.SECONDS) //wait 5 seconds
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())  //Declaring that our observable be observed on the main thread
                    .subscribe(new Subscriber<Data>() {//Attaching subscriber of type ____SearchResponse to the Observable
                        @Override
                        public void onCompleted() {//This is a callback that notifies the observer of the end of the sequence.
                            if (isViewAttached()) {
                                getView().showContent();//If view IS attached then show the searchList
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            if (isViewAttached()) {
                                getView().showError(e);
                            }
                        }

                        @Override
                        public void onNext(Data data) {
                            //Todo: convert this Retrofit object into an immutable pojo too
                            // map internal UI objects to Realm objects
                            if (isViewAttached()) {
                                getView().setData(data.getAlbum());
                            }
                        }

                    });
    }
        //getView().showLoading;


    public void loadRealmData(){
        //getView().showLoading;
    }

    public void refreshData(boolean var1){
        //getView().showLoading;
    }

    void handleError(Throwable var1){
        //getView().showError();
    }

    public void injectDependencies(AppComponent appComponent){
        appComponent.inject(this);
    }

}
