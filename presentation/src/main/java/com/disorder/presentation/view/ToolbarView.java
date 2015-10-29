package com.disorder.presentation.view;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface ToolbarView {

    @IntDef({NEWS, USER, FAVOURITES, CREATE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Page {
    }

    public static final int NEWS = 0;
    public static final int USER = 1;
    public static final int FAVOURITES = 2;
    public static final int CREATE = 3;

    void showPage(@Page int page);
}
