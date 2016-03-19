package com.johnnymolina.workoutswithimgur.views;

import android.os.Bundle;

import com.hannesdorfmann.mosby.mvp.viewstate.RestorableViewState;
import com.johnnymolina.workoutswithimgur.interfaces.MainActivityView;

/**
 * Created by Johnny on 3/18/2016.
 */
public class MainFragmentViewState implements RestorableViewState<MainActivityView> {

    private final String KEY_STATE = getClass().getName().toString() + "currentState";

    private final int STATE_SHOWING_VIEW = 0;
    private final int STATE_SHOWING_LOADING = 1;

    private int currentState = 0;


    @Override
    public void saveInstanceState(Bundle out) {
        out.putInt(KEY_STATE, currentState);
    }

    @Override
    public RestorableViewState<MainActivityView> restoreInstanceState(Bundle in) {
        currentState = in.getInt(KEY_STATE);
        return restoreInstanceState(in);
    }


    @Override
    public void apply(MainActivityView mainActivityView, boolean b) {

        if (currentState == STATE_SHOWING_VIEW) {
            mainActivityView.showView();
        }
        else {
            mainActivityView.showLoading();
        }
    }

    public void setStateShowView() {
        currentState = STATE_SHOWING_VIEW;
    }

    public void setStateShowLoading() {
        currentState = STATE_SHOWING_LOADING;
    }

}
