package com.disorder.presentation.presenter;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

public interface Presenter<T> {

    @CheckResult
    T getView();

    void attachView(@NonNull T view);

    void detachView();
}
