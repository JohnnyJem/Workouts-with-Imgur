package com.johnnymolina.workoutswithimgur.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.johnnymolina.workoutswithimgur.mosby.MosbyFragment;

/**
 * Created by Johnny on 3/7/2016.
 */
public abstract class BaseFragment extends MosbyFragment {


    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = this.getActivity();
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
    }

    @Override public void onDestroy() {
        super.onDestroy();
    }

}
