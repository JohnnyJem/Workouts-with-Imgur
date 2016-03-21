package com.johnnymolina.workoutswithimgur.mosby;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.hannesdorfmann.mosby.mvp.delegate.ActivityMvpDelegate;
import com.hannesdorfmann.mosby.mvp.delegate.ActivityMvpViewStateDelegateCallback;
import com.hannesdorfmann.mosby.mvp.delegate.ActivityMvpViewStateDelegateImpl;
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

/**
 * Created by Johnny on 3/20/2016.
 */
public abstract class MosbyMvpViewStateActivity
        <V extends MvpView, P extends MvpPresenter<V>> extends MosbyMvpActivity<V, P>
        implements ActivityMvpViewStateDelegateCallback<V, P> {

    protected ViewState<V> viewState;
    protected boolean restoringViewState = false;

    public MosbyMvpViewStateActivity() {
    }

    protected ActivityMvpDelegate<V, P> getMvpDelegate() {
        if(this.mvpDelegate == null) {
            this.mvpDelegate = new ActivityMvpViewStateDelegateImpl(this);
        }

        return this.mvpDelegate;
    }

    public ViewState<V> getViewState() {
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

    public abstract ViewState<V> createViewState();
}

