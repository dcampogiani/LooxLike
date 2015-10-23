package com.disorder.presentation.utils;

import rx.Observable;
import rx.Scheduler;

public class MainThreadAndBackgroundRxScheduler implements RxScheduler {

    private Scheduler mainThread;
    private Scheduler background;

    public MainThreadAndBackgroundRxScheduler(Scheduler mainThread, Scheduler background) {
        this.mainThread = mainThread;
        this.background = background;
    }

    @Override
    public <T> Observable<T> schedule(Observable<T> observable) {
        return observable.observeOn(mainThread).subscribeOn(background);
    }
}
