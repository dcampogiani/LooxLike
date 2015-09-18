package com.disorder.presentation.utils;


import rx.Observable;

public interface RxScheduler {

    <T> Observable<T> schedule(Observable<T> observable);
}
