package com.disorder.presentation.view;

import android.support.annotation.IntDef;

import com.disorder.presentation.model.NewsPost;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface NewsView {

    @IntDef({MALE, FEMALE})
    @Retention(RetentionPolicy.SOURCE)
    @interface Gender {
    }

    public static final int MALE = 0;
    public static final int FEMALE = 1;

    void updateModel(NewsPost[] model);

    void showLoading();

    void hideLoading();

    void showError(String errorMessage);

    void hideError();
}
