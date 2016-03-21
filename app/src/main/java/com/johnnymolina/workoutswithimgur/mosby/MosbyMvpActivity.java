package com.johnnymolina.workoutswithimgur.mosby;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.hannesdorfmann.mosby.mvp.delegate.ActivityMvpDelegate;
import com.hannesdorfmann.mosby.mvp.delegate.ActivityMvpDelegateCallback;
import com.hannesdorfmann.mosby.mvp.delegate.ActivityMvpDelegateImpl;

/**
 * Created by Johnny on 3/20/2016.
 */
public abstract class MosbyMvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends MosbyActivity implements ActivityMvpDelegateCallback<V, P>, MvpView {
    protected ActivityMvpDelegate mvpDelegate;
    protected P presenter;
    protected boolean retainInstance;

    public MosbyMvpActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getMvpDelegate().onCreate(savedInstanceState);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.getMvpDelegate().onDestroy();
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.getMvpDelegate().onSaveInstanceState(outState);
    }

    protected void onPause() {
        super.onPause();
        this.getMvpDelegate().onPause();
    }

    protected void onResume() {
        super.onResume();
        this.getMvpDelegate().onResume();
    }

    protected void onStart() {
        super.onStart();
        this.getMvpDelegate().onStart();
    }

    protected void onStop() {
        super.onStop();
        this.getMvpDelegate().onStop();
    }

    protected void onRestart() {
        super.onRestart();
        this.getMvpDelegate().onRestart();
    }

    public void onContentChanged() {
        super.onContentChanged();
        this.getMvpDelegate().onContentChanged();
    }

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.getMvpDelegate().onPostCreate(savedInstanceState);
    }

    @NonNull
    public abstract P createPresenter();

    @NonNull
    protected ActivityMvpDelegate<V, P> getMvpDelegate() {
        if(this.mvpDelegate == null) {
            this.mvpDelegate = new ActivityMvpDelegateImpl(this);
        }

        return this.mvpDelegate;
    }

    @NonNull
    public P getPresenter() {
        return this.presenter;
    }

    public void setPresenter(@NonNull P presenter) {
        this.presenter = presenter;
    }

    @NonNull
    public V getMvpView() {
        return (V) this;
    }

    public boolean isRetainInstance() {
        return this.retainInstance;
    }

    public boolean shouldInstanceBeRetained() {
        return this.retainInstance && this.isChangingConfigurations();
    }

    public void setRetainInstance(boolean retainInstance) {
        this.retainInstance = retainInstance;
    }

    public Object onRetainNonMosbyCustomNonConfigurationInstance() {
        return null;
    }

    public final Object onRetainCustomNonConfigurationInstance() {
        return this.getMvpDelegate().onRetainCustomNonConfigurationInstance();
    }

    public final Object getNonMosbyLastCustomNonConfigurationInstance() {
        return this.getMvpDelegate().getNonMosbyLastCustomNonConfigurationInstance();
    }
}
