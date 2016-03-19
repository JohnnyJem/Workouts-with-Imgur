package com.johnnymolina.workoutswithimgur.views;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.johnnymolina.workoutswithimgur.R;
import com.johnnymolina.workoutswithimgur.base.BaseActivity;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import butterknife.Bind;

/**
 * An Activity holding 3 fragment tabs.
 */
public class MainActivity extends BaseActivity {
    private final String TAG = getClass().getSimpleName();
    public static final String KEY_SHOW_ACTION =
            "com.johnnymolina.workoutswithimgur.views.MainActivity.SHOW_ACTION";

    public static final String FRAGMENT_TAG_MAIN = "mainFragmentTag";
    public static final String FRAGMENT_TAG_DETAILS = "detailsFragmentTag";

    public static final String VIEWSTATE0 = "0";
    public static final String VIEWSTATE1 = "1";
    public static final String VIEWSTATE2 = "2";

    MainFragment mainFragment;
    DetailsFragment detailsFragment;

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

        // Handle Toolbar and Drawer setup
        if (toolbar!= null){
            setSupportActionBar(toolbar);
            toolbar.setTitle(R.string.drawer_item_menu_drawer);
            result = new DrawerBuilder()
                    .withActivity(this)
                    .withToolbar(toolbar)
                    .inflateMenu(R.menu.drawer_menu)
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                            if (drawerItem instanceof Nameable) {
                                Toast.makeText(MainActivity.this, ((Nameable) drawerItem).getName().getText(MainActivity.this), Toast.LENGTH_SHORT).show();
                            }
                            return false;
                        }
                    }).build();
        }

        // Check for previous fragments
        mainFragment = findMainFragment();
        detailsFragment = findDetailsFragment();

        if (detailsFragment != null) {
            // details fragment available, so make it visible
            rightPane.setVisibility(View.VISIBLE);
        }

        if (paneContainer != null) {
            // Enable animation
            LayoutTransition transition = new LayoutTransition();
            transition.enableTransitionType(LayoutTransition.CHANGING);
            paneContainer.setLayoutTransition(transition);
        }

        if (mainFragment == null) {
            // First app start, so start with this
            showViews(true);
        }

    }

    @Override protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        String showAction = intent.getStringExtra(KEY_SHOW_ACTION);
        //TODO: get parcelable extras here to show details.
    }

    private void showViews(boolean removeDetailsFragment) {
        MainFragment fragment = new MainFragmentBuilder().build();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.leftPane, fragment, FRAGMENT_TAG_MAIN)
                .commit();

        if (removeDetailsFragment){
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

    private MainFragment findMainFragment(){
        return (MainFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_MAIN);
    }

    /**
     * @return true if a fragment has been removed, otherwise false
     */
    private boolean removeDetailsFragment() {
        DetailsFragment detailsFragment = findDetailsFragment();
        if (detailsFragment != null) {
            rightPane.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction().remove(detailsFragment).commit();
            return true;
        }

        return false;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
        return super.onOptionsItemSelected(item);
    }

}


