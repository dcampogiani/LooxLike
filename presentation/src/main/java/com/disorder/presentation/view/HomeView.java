package com.disorder.presentation.view;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface HomeView {

    @IntDef({NEWS, USER, FAVOURITES})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Page {
    }

    public static final int NEWS = 0;
    public static final int USER = 1;
    public static final int FAVOURITES = 2;

    void showPage(@Page int page);
}
