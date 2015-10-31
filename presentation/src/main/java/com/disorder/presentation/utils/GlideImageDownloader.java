package com.disorder.presentation.utils;


import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.disorder.presentation.R;

public class GlideImageDownloader implements ImageDownloader {

    private final Context mContext;
    private static final
    @DrawableRes
    int placeholder = R.drawable.ic_placeholder;

    public GlideImageDownloader(Context mContext) {
        this.mContext = mContext.getApplicationContext();
    }

    @Override
    public void request(String url, ImageView target) {
        request(url, target, null);
    }

    @Override
    public void request(String url, ImageView target, Animation animation) {
        DrawableRequestBuilder<String> builder = Glide.with(mContext).load(url).placeholder(GlideImageDownloader.placeholder);
        if (animation == Animation.NONE)
            builder.dontAnimate();
        else
            builder.crossFade();
        builder.into(target);
    }
}
