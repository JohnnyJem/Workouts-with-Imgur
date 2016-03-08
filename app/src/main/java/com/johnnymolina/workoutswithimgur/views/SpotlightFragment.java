package com.johnnymolina.workoutswithimgur.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.johnnymolina.workoutswithimgur.R;
import com.johnnymolina.workoutswithimgur.base.BaseFragment;

@FragmentWithArgs
public class SpotlightFragment extends BaseFragment {

    private String mItem;
    private String mDetails;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SpotlightFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            mItem = "Album Detail Frag";
            mDetails = "This is where the details go";
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mDetails);
            }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.spotlight_frag, container, false);
        ((TextView) rootView.findViewById(R.id.album_detail)).setText(mDetails);

        return rootView;
    }
}
