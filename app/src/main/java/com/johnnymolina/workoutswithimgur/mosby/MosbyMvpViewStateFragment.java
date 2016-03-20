package com.johnnymolina.workoutswithimgur.mosby;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.hannesdorfmann.mosby.mvp.delegate.FragmentMvpDelegate;
import com.hannesdorfmann.mosby.mvp.delegate.FragmentMvpViewStateDelegateImpl;
import com.hannesdorfmann.mosby.mvp.delegate.MvpViewStateDelegateCallback;
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

/**
 * Created by Johnny on 3/19/2016.
 */
public abstract class MosbyMvpViewStateFragment<V extends MvpView, P extends MvpPresenter<V>> extends MvpFragment<V, P> implements MvpViewStateDelegateCallback<V, P> {
    protected ViewState<V> viewState;
    private boolean restoringViewState = false;

    public MosbyMvpViewStateFragment() {
    }

    @NonNull
    public abstract ViewState createViewState();

    protected FragmentMvpDelegate<V, P> getMvpDelegate() {
        if(this.mvpDelegate == null) {
            this.mvpDelegate = new FragmentMvpViewStateDelegateImpl<>(this);
        }

        return this.mvpDelegate;
    }

    public ViewState getViewState() {
        return this.viewState;
    }

    public void setViewState(ViewState<V> viewState) {
        this.viewState = viewState;
    }

    public void setRestoringViewState(boolean restoringViewState) {
        this.restoringViewState = restoringViewState;
    }

    public boolean isRestoringViewState() {
        return this.restoringViewState;
    }

    public void onViewStateInstanceRestored(boolean instanceStateRetained) {
    }
}
