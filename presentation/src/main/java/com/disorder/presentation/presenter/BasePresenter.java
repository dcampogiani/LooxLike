package com.disorder.presentation.presenter;


import android.support.annotation.NonNull;

public abstract class BasePresenter<T> implements Presenter<T> {

    private T mView;

    @Override
    public T getView() {
        return mView;
    }

    @Override
    public void attachView(@NonNull T view) {
        mView = view;

    }

    @Override
    public void detachView() {
        mView = null;
    }
}
