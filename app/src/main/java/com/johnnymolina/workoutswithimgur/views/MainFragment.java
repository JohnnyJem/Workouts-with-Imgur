package com.johnnymolina.workoutswithimgur.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.hannesdorfmann.mosby.mvp.viewstate.RestorableViewState;
import com.johnnymolina.workoutswithimgur.ImgurApplication;
import com.johnnymolina.workoutswithimgur.R;
import com.johnnymolina.workoutswithimgur.mosby.MosbyMvpViewStateFragment;
import com.johnnymolina.workoutswithimgur.network.api.model.Album;
import com.johnnymolina.workoutswithimgur.other.RxBus;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import io.realm.Realm;
import timber.log.Timber;

@FragmentWithArgs
public class MainFragment extends MosbyMvpViewStateFragment
        <MainFragmentView, MainFragmentPresenter>
        implements MainFragmentView {
    public static final int VIEWFLIPPER_RESULTS = 0;
    public static final int VIEWFLIPPER_LOADING = 1;

    @Inject ImgurApplication imgurApplication;
    @Inject RxBus rxBus;
    @Inject Realm realm;

    Album album;

    @Bind(R.id.view_flipper) ViewFlipper viewFlipper;
    @Bind(R.id.button_request_data) Button button;
    @Bind(R.id.tv_show_data) TextView dataTextView;


    public MainFragment() {

    }


    /*---------------------Lifecycle Methods----------------------------*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    protected void injectDependencies() {
        super.injectDependencies();
        ((ImgurApplication) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment, container, false);

        return rootView;
    }

    /* ------------------Presenter Interaction Methods-----------------*/

    @Override
    public void loadData(boolean var1) {

    }

    @Override
    public void refreshData(boolean var1) {

    }

    @Override
    public void showLoading(boolean var1) {
        getViewState().setStateShowLoading();
        viewFlipper.setDisplayedChild(VIEWFLIPPER_LOADING);
    }

    @Override
    public void setData(Album album) {
        if (album!=null){
            this.album = album;
            dataTextView.setText(album.getLink());
        }
    }

    @Override
    public void showError(Throwable e) {
        viewFlipper.setDisplayedChild(VIEWFLIPPER_RESULTS);
        Timber.e(e,e.getMessage().toString());
        Toast.makeText(getContext(), "error: " + e.getMessage().toString(), Toast.LENGTH_LONG).show();

    }

    @Override public void showContent() {
        getViewState().setStateShowView();
        viewFlipper.setDisplayedChild(VIEWFLIPPER_RESULTS);
        //contentView.setRefreshing(false);
    }


    /* ---------------------Default Presenter Methods-----------------*/
    @NonNull @Override
    public MainFragmentPresenter createPresenter() {
        return new MainFragmentPresenter(imgurApplication.getAppComponent());
    }

    @NonNull @Override
    public MainFragmentPresenter getPresenter() {
        return super.getPresenter();
    }

    /* ---------------------Viewstate Methods-------------------------*/
    @NonNull @Override
    public RestorableViewState createViewState() {
        return new MainFragmentViewState();
    }

    @Override
    public void onNewViewStateInstance() {
        showContent();
    }

    @Override
    public MainFragmentViewState getViewState() {
        return (MainFragmentViewState) super.getViewState();
    }

     /* ----------------------------Other Methods-----------------*/

//    protected void setup(){
//
//    }

    @OnClick(R.id.button_request_data)
    public void requestData(){
        presenter.loadData("JNzjB");
    }

}

