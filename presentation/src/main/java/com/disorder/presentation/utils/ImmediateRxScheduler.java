package com.disorder.presentation.utils;


import rx.Observable;
import rx.schedulers.Schedulers;

public class ImmediateRxScheduler implements RxScheduler {
    @Override
    public <T> Observable<T> schedule(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.immediate()).observeOn(Schedulers.immediate());
    }
}
