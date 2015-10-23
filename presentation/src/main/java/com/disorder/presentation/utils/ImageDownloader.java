package com.disorder.presentation.utils;

import android.widget.ImageView;

public interface ImageDownloader {

    void request(String url, ImageView target);
}
