package com.johnnymolina.workoutswithimgur.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.fivehundredpx.greedolayout.GreedoLayoutManager;
import com.fivehundredpx.greedolayout.GreedoSpacingItemDecoration;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.hannesdorfmann.mosby.mvp.viewstate.RestorableViewState;
import com.johnnymolina.workoutswithimgur.ImgurApplication;
import com.johnnymolina.workoutswithimgur.R;
import com.johnnymolina.workoutswithimgur.mosby.MosbyMvpViewStateFragment;
import com.johnnymolina.workoutswithimgur.network.api.model.Album;
import com.johnnymolina.workoutswithimgur.views.other.MeasUtils;
import com.johnnymolina.workoutswithimgur.other.RxBus;
import com.johnnymolina.workoutswithimgur.views.adapters.DownloadedMediaAdapter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import icepick.State;
import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by Johnny on 3/8/2016.
 */

@FragmentWithArgs
public class DownloadFragment
        extends MosbyMvpViewStateFragment<DownloadFragmentView, DownloadFragmentPresenter>
        implements DownloadFragmentView {

    public static final int VIEWFLIPPER_RESULTS = 0;
    public static final int VIEWFLIPPER_LOADING = 1;

    @Inject ImgurApplication imgurApplication;
    @Inject RxBus rxBus;
    @Inject Realm realm;


    @Arg (required = false)
    String query;

    Album album;

    @State int columnCount = 2;

    @Bind(R.id.view_flipper) ViewFlipper viewFlipper;
    @Bind(R.id.download_fragment_fab) FloatingActionButton floatingActionButton;
    @Bind(R.id.rv_downloaded) RecyclerView recyclerView;
    @Bind(R.id.cl_download_fragment) CoordinatorLayout coordinatorLayout;


    GreedoLayoutManager layoutManager;
    DownloadedMediaAdapter adapter;

    public DownloadFragment() {

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
        if (query!=null){
            loadData(query);
        }else {
            Toast.makeText(this.getContext(),"No query given",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.download_fragment, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        columnCount = getResources().getBoolean(R.bool.isLand) ? 4 : 2;

        floatingActionButton.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    /* ------------------Presenter Interaction Methods-----------------*/

    @Override
    public void loadData(String query) {
        presenter.loadData(query);
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
            adapter = new DownloadedMediaAdapter(getContext(),album.getImages());
            layoutManager = new GreedoLayoutManager(adapter);

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            DisplayMetrics metrics = getResources().getDisplayMetrics();
            int maxRowHeight = metrics.heightPixels / 3;
            layoutManager.setMaxRowHeight(maxRowHeight);

            int spacing = MeasUtils.dpToPx(4, getContext());
            recyclerView.addItemDecoration(new GreedoSpacingItemDecoration(spacing));
            Snackbar.make(coordinatorLayout,
                    "Loaded " + album.getTitle() + " images("+ album.getImagesCount() +")",
                    Snackbar.LENGTH_SHORT).show();
        }else {
            Snackbar.make(coordinatorLayout,
                    "Data not Loaded",
                    Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showError(Throwable e) {
        viewFlipper.setDisplayedChild(VIEWFLIPPER_RESULTS);
        Timber.e(e,e.getMessage().toString());
        Snackbar.make(coordinatorLayout, "error: " + e.getMessage().toString(),Snackbar.LENGTH_SHORT).show();

        if (isFragmentAlive()){
            String message = !((MainActivity) getActivity()).isNetworkAvailable() ?
                    "No Network Connection" :"Invalid Album Id";
            Snackbar.make(coordinatorLayout, message,Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showSuccess(String message, boolean isVisible) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override public void showContent() {
        getViewState().setStateShowView();
        viewFlipper.setDisplayedChild(VIEWFLIPPER_RESULTS);
    }


    /* ---------------------Default Presenter Methods-----------------*/
    @NonNull
    @Override
    public DownloadFragmentPresenter createPresenter() {
        return new DownloadFragmentPresenter(imgurApplication.getAppComponent());
    }

    @NonNull @Override
    public DownloadFragmentPresenter getPresenter() {
        return super.getPresenter();
    }

    /* ---------------------Viewstate Methods-------------------------*/
    @NonNull @Override
    public RestorableViewState createViewState() {
        return new DownloadFragmentViewState();
    }

    @Override
    public void onNewViewStateInstance() {
        showContent();
    }

    @Override
    public DownloadFragmentViewState getViewState() {
        return (DownloadFragmentViewState) super.getViewState();
    }

     /* ----------------------------Other Methods-----------------*/

    @OnClick(R.id.download_fragment_fab)
    public void downloadIntoRealm(){
        presenter.saveToRealm();
    }


}