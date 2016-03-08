package com.johnnymolina.workoutswithimgur.mosby.scheduler;


import com.johnnymolina.workoutswithimgur.mosby.lce.MvpLceRxPresenter;
import rx.Observable;

/**
 * A {@link Observable.Transformer} that is used to set the schedulers for an Observable that can
 * be subscribed by {@link MvpLceRxPresenter}.
 *
 * @author Hannes Dorfmann
 * @since 1.0.0
 */
public interface SchedulerTransformer<T> extends Observable.Transformer<T, T> {
}
