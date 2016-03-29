package com.johnnymolina.workoutswithimgur.views;

import android.os.Bundle;

import com.hannesdorfmann.mosby.mvp.viewstate.RestorableViewState;

/**
 * Created by Johnny on 3/28/2016.
 */
public class DownloadFragmentViewState implements RestorableViewState<DownloadFragmentView> {

    private final String KEY_STATE = getClass().getName().toString() + "currentState";

    private final int STATE_SHOWING_VIEW = 0;
    private final int STATE_SHOWING_LOADING = 1;

    private int currentState = 0;


    @Override
    public void saveInstanceState(Bundle out) {
        out.putInt(KEY_STATE, currentState);
    }

    @Override
    public RestorableViewState<DownloadFragmentView> restoreInstanceState(Bundle in) {
        currentState = in.getInt(KEY_STATE);
        return null;
    }


    @Override
    public void apply(DownloadFragmentView viewCallback, boolean b) {

        if (currentState == STATE_SHOWING_VIEW) {
            viewCallback.showContent();
        }
        else {
            viewCallback.showLoading(true);
        }
    }

    public void setStateShowView() {
        currentState = STATE_SHOWING_VIEW;
    }

    public void setStateShowLoading() {
        currentState = STATE_SHOWING_LOADING;
    }

}
