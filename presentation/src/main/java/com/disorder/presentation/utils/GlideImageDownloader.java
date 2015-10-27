package com.disorder.presentation.utils;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GlideImageDownloader implements ImageDownloader {

    private final Context mContext;
    private static final Drawable placeholder = new ColorDrawable(Color.TRANSPARENT);

    public GlideImageDownloader(Context mContext) {
        this.mContext = mContext.getApplicationContext();
    }

    @Override
    public void request(String url, ImageView target) {
        Glide.with(mContext)
                .load(url)
                .centerCrop()
                .placeholder(placeholder)
                .crossFade()
                .into(target);
    }
}
