package com.johnnymolina.workoutswithimgur.mosby;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.hannesdorfmann.mosby.mvp.delegate.FragmentMvpDelegate;
import com.hannesdorfmann.mosby.mvp.delegate.FragmentMvpDelegateImpl;
import com.hannesdorfmann.mosby.mvp.delegate.MvpDelegateCallback;

public abstract class MosbyMvpFragment<V extends MvpView, P extends MvpPresenter<V>> extends MosbyFragment implements MvpDelegateCallback<V, P>, MvpView {
    protected FragmentMvpDelegate<V, P> mvpDelegate;
    protected P presenter;

    public MosbyMvpFragment() {
    }

    public abstract P createPresenter();

    @NonNull
    protected FragmentMvpDelegate<V, P> getMvpDelegate() {
        if(this.mvpDelegate == null) {
            this.mvpDelegate = new FragmentMvpDelegateImpl(this);
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

    public boolean isRetainInstance() {
        return this.getRetainInstance();
    }

    public boolean shouldInstanceBeRetained() {
        FragmentActivity activity = this.getActivity();
        boolean changingConfig = activity != null && activity.isChangingConfigurations();
        return this.getRetainInstance() && changingConfig;
    }

    @NonNull
    public V getMvpView() {
        return (V) this;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.getMvpDelegate().onViewCreated(view, savedInstanceState);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.getMvpDelegate().onDestroyView();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getMvpDelegate().onCreate(savedInstanceState);
    }

    public void onDestroy() {
        super.onDestroy();
        this.getMvpDelegate().onDestroy();
    }

    public void onPause() {
        super.onPause();
        this.getMvpDelegate().onPause();
    }

    public void onResume() {
        super.onResume();
        this.getMvpDelegate().onResume();
    }

    public void onStart() {
        super.onStart();
        this.getMvpDelegate().onStart();
    }

    public void onStop() {
        super.onStop();
        this.getMvpDelegate().onStop();
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getMvpDelegate().onActivityCreated(savedInstanceState);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.getMvpDelegate().onAttach(activity);
    }

    public void onDetach() {
        super.onDetach();
        this.getMvpDelegate().onDetach();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.getMvpDelegate().onSaveInstanceState(outState);
    }
}
