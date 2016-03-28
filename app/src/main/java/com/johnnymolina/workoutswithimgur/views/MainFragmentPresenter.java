package com.johnnymolina.workoutswithimgur.views;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.johnnymolina.workoutswithimgur.AppComponent;
import com.johnnymolina.workoutswithimgur.network.ImgurService;
import com.johnnymolina.workoutswithimgur.network.api.model.Album;
import com.johnnymolina.workoutswithimgur.network.api.model.Data;
import com.johnnymolina.workoutswithimgur.network.api.model.Image;
import com.johnnymolina.workoutswithimgur.network.api.model.realm.RealmAlbum;
import com.johnnymolina.workoutswithimgur.network.api.model.realm.RealmImage;
import com.johnnymolina.workoutswithimgur.other.RxBus;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmList;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Johnny on 3/19/2016.
 * //Todo: Make sure that: remove all "import android.*"
 * statements from your PresenterImpl classes and resolve the
 * resulting errors by utilizing interfaces and effectively "hiding" the Android framework
 * from the Presenters.
 * This way, they'll be much easier to perform testing on!
 * Another thing to consider: Question, when using something like Glide that requires a context.
 * Should Glide not be in the presenter?
 * A: You could have a void loadImage(String url) in your view, which actually holds
 * the context, and so the presenter only would have to call that method in the view,
 * abstracting the presenter from the framework
 */
public class MainFragmentPresenter extends MvpBasePresenter<MainFragmentView> {

    @Inject RxBus rxBus;
    @Inject Realm realm;
    @Inject ImgurService imgurService;

    CompositeSubscription compositeSubscription;
    Album album;
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

           album = null;


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
                                getView().showSuccess("Data Loaded", true);
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
                            // map internal UI objects to Realm objects
                            if (isViewAttached()) {
                                getView().setData(data.getAlbum());
                            }
                            album = data.getAlbum();

                        }

                    });
    }
        //getView().showLoading;


    public void loadRealmData(){
        //getView().showLoading;
    }

    public void saveToRealm(){
        if (album != null) {
            // begin & end transcation calls are done for you
            RealmAlbum realmAlbum = new RealmAlbum();
            realmAlbum.setId(album.getId());
            realmAlbum.setTitle(album.getTitle());
            realmAlbum.setDescription(album.getDescription());
            album.setLink(album.getLink());
            album.setImagesCount(album.getImagesCount());

            RealmList<RealmImage> realmImageList = new RealmList<RealmImage>();
            for (int i = 0; i < album.getImages().size(); i++) {
                Image image = album.getImages().get(i);
                RealmImage realmImage = new RealmImage();
                realmImage.setAlbumId(album.getId());
                realmImage.setAlbumId(image.getId());
                realmImage.setTitle(image.getTitle());
                realmImage.setDescription(image.getDescription());
                realmImage.setDatetime(image.getDatetime());
                realmImage.setType(image.getType());
                realmImage.setAnimated(image.isAnimated());
                realmImage.setWidth(image.getWidth());
                realmImage.setHeight(image.getHeight());
                realmImage.setSize(image.getSize());
                realmImage.setViews(image.getViews());
                //realmImage.setBandwidth();
                realmImage.setLink(image.getLink());
                realmImageList.add(realmImage);
            }
            realmAlbum.setImages(realmImageList);

            realm.beginTransaction();
            realm.copyToRealm(realmAlbum);
            realm.commitTransaction();
            if (isViewAttached()){
                getView().showSuccess("Succesfully saved to Realm DB", false);
            }
    }
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
