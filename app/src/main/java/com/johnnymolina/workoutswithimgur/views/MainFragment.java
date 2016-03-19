package com.johnnymolina.workoutswithimgur.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.johnnymolina.workoutswithimgur.R;
import com.johnnymolina.workoutswithimgur.base.BaseFragment;
import com.johnnymolina.workoutswithimgur.interfaces.MainFragmentView;

@FragmentWithArgs
public class MainFragment extends BaseFragment implements MainFragmentView {
    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment, container, false);

        return rootView;
    }

    @Override
    public void setData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void showView() {

    }
}

