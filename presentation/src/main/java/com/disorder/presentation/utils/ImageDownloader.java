package com.disorder.presentation.utils;

import android.widget.ImageView;

public interface ImageDownloader {

    enum Animation {
        NONE, CROSS_FADE
    }

    void request(String url, ImageView target);

    void request(String url, ImageView target, Animation animation);
}
