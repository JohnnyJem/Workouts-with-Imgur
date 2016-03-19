package com.johnnymolina.workoutswithimgur.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.johnnymolina.workoutswithimgur.R;
import com.johnnymolina.workoutswithimgur.base.BaseFragment;

/**
 * Created by Johnny on 3/8/2016.
 */

@FragmentWithArgs
public class DetailsFragment extends BaseFragment{
    public DetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.details_fragment, container, false);

        return rootView;
    }
}

