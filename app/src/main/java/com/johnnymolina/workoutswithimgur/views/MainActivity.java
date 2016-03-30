package com.johnnymolina.workoutswithimgur.views;

import android.animation.LayoutTransition;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.hannesdorfmann.mosby.mvp.viewstate.RestorableViewState;
import com.jaeger.library.StatusBarUtil;
import com.johnnymolina.workoutswithimgur.ImgurApplication;
import com.johnnymolina.workoutswithimgur.R;
import com.johnnymolina.workoutswithimgur.mosby.MosbyMvpViewStateActivity;
import com.johnnymolina.workoutswithimgur.other.RxBus;
import com.mikepenz.materialdrawer.Drawer;

import javax.inject.Inject;

import butterknife.Bind;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * An Activity holding 3 fragment tabs.
 */
public class MainActivity
        extends MosbyMvpViewStateActivity<MainActivityView, MainActivityPresenter>
        implements MainActivityView {
    private final String TAG = getClass().getSimpleName();
    public static final String KEY_SHOW_ACTION = "com.johnnymolina.workoutswithimgur.views.MainActivity.SHOW_ACTION";
    public static final String FRAGMENT_TAG_MAIN = "mainFragmentTag";
    public static final String FRAGMENT_TAG_DETAILS = "detailsFragmentTag";
    public static final String FRAGMENT_TAG_DOWNLOAD = "downloadFragmentTag";

    public static final int VIEWFLIPPER_RESULTS = 0;
    public static final int VIEWFLIPPER_LOADING = 1;

    public static final String VIEWSTATE0 = "0";
    public static final String VIEWSTATE1 = "1";
    public static final String VIEWSTATE2 = "2";

    private CompositeSubscription subscriptions;

    @Inject ImgurApplication imgurApplication;
    @Inject RxBus rxBus;

    MainFragment mainFragment;
    DetailsFragment detailsFragment;

    @Bind(R.id.view_flipper) ViewFlipper viewFlipper;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.leftPane) ViewGroup leftPane;
    @Nullable @Bind(R.id.rightPane) ViewGroup rightPane;
    // contains leftPane + rightPane
    @Nullable @Bind(R.id.paneContainer) ViewGroup paneContainer;
    private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

//        // Handle Toolbar and Drawer setup
//        if (toolbar != null) {
//            setSupportActionBar(toolbar);
//            toolbar.setTitle(R.string.drawer_item_menu_drawer);
//            result = new DrawerBuilder()
//                    .withActivity(this)
//                    .withToolbar(toolbar)
//                    .inflateMenu(R.menu.drawer_menu)
//                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
//                        @Override
//                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
//                            if (drawerItem instanceof Nameable) {
//                                Toast.makeText(MainActivity.this, ((Nameable) drawerItem).getName().getText(MainActivity.this), Toast.LENGTH_SHORT).show();
//                            }
//                            return false;
//                        }
//                    }).build();
//        }
        setSupportActionBar(toolbar);
        StatusBarUtil.setTranslucent(MainActivity.this);
        StatusBarUtil.setTransparent(MainActivity.this);


        // Check for previous fragments
        mainFragment = findMainFragment();
        detailsFragment = findDetailsFragment();

        if (paneContainer != null) {
            // Enable animation
            Timber.i("paneContainer is not null");
            LayoutTransition transition = new LayoutTransition();
            transition.enableTransitionType(LayoutTransition.CHANGING);
            paneContainer.setLayoutTransition(transition);
        }

        if (mainFragment == null) {
            // First app start, so start with this
            if (savedInstanceState==null) {
                initStartupViews(true);
            }else {

            }
        }

        if (detailsFragment != null) {
            // details fragment available, so make it visible
            Timber.i("DetailFragment is not null");
            rightPane.setVisibility(View.VISIBLE);
        }



    }

    @Override
    protected void onStart() {
        super.onStart();
        subscriptions = new CompositeSubscription();
        subscriptions
                .add(rxBus.toObserverable()//
                        .compose(bindToLifecycle())//optional since we are using a compositeSubscription();
                        .subscribe(new Action1<Object>() {
                            @Override
                            public void call(Object event) {
                                //Do something with the object passed through the event bus.
                                // If many objects use switch.
                            }
                        }));
    }

    @Override
    protected void onStop() {
        subscriptions.unsubscribe();//make sure to unsubscribe.
        super.onStop();
    }

    //Init Injection of our dependencies. This occurs before super.OnCreate() is called;
    @Override
    protected void injectDependencies() {
        super.injectDependencies();
        ((ImgurApplication) getApplication()).getAppComponent().inject(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        String showAction = intent.getStringExtra(KEY_SHOW_ACTION);
        //TODO: get parcelable extras here to show details.
    }

    private void initStartupViews(boolean removeDetailsFragment) {
        MainFragment fragment = new MainFragmentBuilder().build();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.leftPane, fragment, FRAGMENT_TAG_MAIN)
                .commit();

        if (removeDetailsFragment) {
            removeDetailsFragment();
        }
    }

    //Todo: Make this be called from an intent
    private void showDetails() {
        rightPane.setVisibility(View.VISIBLE);
        DetailsFragment fragment = new DetailsFragmentBuilder().build();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rightPane, fragment, FRAGMENT_TAG_DETAILS)
                .commit();
    }

    private DetailsFragment findDetailsFragment() {
        return (DetailsFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_DETAILS);
    }

    private MainFragment findMainFragment() {
        return (MainFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_MAIN);
    }

    private DownloadFragment findDownloadFragment(){
        return (DownloadFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_DOWNLOAD);
    }

    /**
     * @return true if a fragment has been removed, otherwise false
     */
    private boolean removeDetailsFragment() {
        DetailsFragment detailsFragment = findDetailsFragment();
        if (detailsFragment != null) {
            if (rightPane != null) {
                rightPane.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().remove(detailsFragment).commit();
                return true;
            }
        }
        return false;
    }


     protected boolean showDownloadFragment(String query) {
         DownloadFragment downloadFragment = findDownloadFragment();
         if (downloadFragment != null) {
             if (rightPane != null) {
                 rightPane.setVisibility(View.GONE);
                 getSupportFragmentManager()
                         .beginTransaction()
                         .replace(R.id.leftPane, downloadFragment)
                         .addToBackStack(FRAGMENT_TAG_MAIN)
                         .commit();
                 return true;
             }
         }else {
             downloadFragment = new DownloadFragmentBuilder()
                     .query(query)
                     .build();
             getSupportFragmentManager()
                     .beginTransaction()
                     .replace(R.id.leftPane, downloadFragment, FRAGMENT_TAG_DOWNLOAD)
                     .commit();
             return true;
         }
         return false;
     }

    private boolean removeDownloadFragment(){
        return false;
    }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.toolbar_menu, menu);
            // Retrieve the SearchView and plug it into SearchManager
            final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
            SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    showDownloadFragment(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            int id = item.getItemId();
            if (id == android.R.id.home) {
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                navigateUpTo(new Intent(this, MainActivity.class));
                return true;
            }
            if (id == R.id.action_search) {
                Timber.i("actionSearchTouched");
            }
            return super.onOptionsItemSelected(item);
        }

        @Override
        public void onBackPressed() {
            if (getFragmentManager().getBackStackEntryCount() > 0 ){
                getFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }
        }

    /* ------------------Presenter Interaction Methods-----------------*/

        @Override
        public void loadData ( boolean var1){

        }

        @Override
        public void showLoading ( boolean var1){
            getViewState().setStateShowLoading();
            viewFlipper.setDisplayedChild(VIEWFLIPPER_LOADING);
        }

        @Override
        public void setData () {

        }

        @Override
        public void showError (Throwable var1,boolean var2){
            viewFlipper.setDisplayedChild(VIEWFLIPPER_RESULTS);
            Toast.makeText(this, "error: " + var1.getMessage().toString(), Toast.LENGTH_LONG).show();
        }

        @Override public void showContent () {
            getViewState().setStateShowView();
            viewFlipper.setDisplayedChild(VIEWFLIPPER_RESULTS);
            //contentView.setRefreshing(false);
        }


    /* ---------------------Default Presenter Methods-----------------*/

        @NonNull @Override
        public MainActivityPresenter createPresenter() {
            return new MainActivityPresenter(imgurApplication.getAppComponent());
        }

        @NonNull @Override
        public MainActivityPresenter getPresenter () {
            return super.getPresenter();
        }

    /* ---------------------Viewstate Methods-----------------*/
        @NonNull @Override
        public RestorableViewState createViewState () {
            return new MainActivityViewState();
        }

        @Override
        public void onNewViewStateInstance () {
            showContent();
        }

        @Override
        public MainActivityViewState getViewState () {
            return (MainActivityViewState) super.getViewState();
        }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    }


