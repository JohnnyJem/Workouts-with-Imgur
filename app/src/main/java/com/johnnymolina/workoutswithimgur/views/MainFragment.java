package com.johnnymolina.workoutswithimgur.views;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.hannesdorfmann.mosby.mvp.viewstate.RestorableViewState;
import com.johnnymolina.workoutswithimgur.ImgurApplication;
import com.johnnymolina.workoutswithimgur.R;
import com.johnnymolina.workoutswithimgur.mosby.MosbyMvpViewStateFragment;
import com.johnnymolina.workoutswithimgur.network.api.model.Album;
import com.johnnymolina.workoutswithimgur.network.api.model.realm.RealmAlbum;
import com.johnnymolina.workoutswithimgur.other.RxBus;
import com.johnnymolina.workoutswithimgur.views.adapters.RVRealmAdapter;

import javax.inject.Inject;

import butterknife.Bind;
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.Realm;
import io.realm.RealmResults;
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
    RVRealmAdapter realmAdapter;

    @Bind(R.id.view_flipper) ViewFlipper viewFlipper;
    @Bind(R.id.main_fragment_fab) FloatingActionButton floatingActionButton;
    @Bind(R.id.main_fragment_srl) SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.main_fragment_rv) RealmRecyclerView realmRecyclerView;
    @Bind(R.id.cl_main_fragment) CoordinatorLayout coordinatorLayout;

    public MainFragment() {

    }
    /*---------------------Lifecycle Methods----------------------------*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        if (savedInstanceState!=null){

        }
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floatingActionButton.setVisibility(View.GONE);

//        realmAdapter = new RVRealmAdapter(getContext(),realmAlbums,true,true);
//        realmRecyclerView.setAdapter(realmAdapter);
//
//        realmRecyclerView.setOnRefreshListener(
//                new RealmRecyclerView.OnRefreshListener() {
//                    @Override
//                    public void onRefresh() {
//                        asyncRefreshAllData();
//                    }
//                }
//        );

//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                // Your code to refresh the list here.
//                // Make sure you call swipeContainer.setRefreshing(false)
//                // once the network request has completed successfully.
//                //asyncRefreshAllData();
//            }
//        });
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
            //init recyclerview
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
        if (isVisible){
            floatingActionButton.setVisibility(View.VISIBLE);
        }else{
            floatingActionButton.setVisibility(View.GONE);
        }
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override public void showContent() {
        getViewState().setStateShowView();
        viewFlipper.setDisplayedChild(VIEWFLIPPER_RESULTS);
        floatingActionButton.setVisibility(View.VISIBLE);
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

    private void asyncRefreshAllData() {
        AsyncTask<Void, Void, Void> remoteItem = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                // Add some delay to the refresh/remove action.
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }

                final RealmResults<RealmAlbum> all = realm.where(RealmAlbum.class).findAll();
                if (all != null) {
                    realm.beginTransaction();
                    all.clear();
                    realm.commitTransaction();
                }
                //realm.close();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                // Remember to CLEAR OUT old items before appending in the new ones
                //adapter.clear
                // ...the data has come back, add new items to your adapter...
                //adapter.addAll(...);
                // Now we call setRefreshing(false) to signal refresh has finished
                swipeRefreshLayout.setRefreshing(false);
            }
        };
        remoteItem.execute();
    }





}



