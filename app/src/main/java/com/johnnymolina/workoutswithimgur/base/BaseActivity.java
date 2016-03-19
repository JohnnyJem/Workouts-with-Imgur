package com.johnnymolina.workoutswithimgur.base;

import android.os.Bundle;

import com.johnnymolina.workoutswithimgur.mosby.MosbyActivity;

import butterknife.ButterKnife;

/**
 * Created by Johnny on 3/7/2016.
 */
public class BaseActivity extends MosbyActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
