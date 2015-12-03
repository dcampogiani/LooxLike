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
    public Request request(String url) {
        return new GlideRequest(url, mContext, placeholder);
    }

    private static class GlideRequest extends Request {

        private final Context context;
        private final int placeholder;

        public GlideRequest(String url, Context context, @DrawableRes int placeholder) {
            super(url);
            this.context = context;
            this.placeholder = placeholder;
        }

        @Override
        public void into(ImageView imageView) {
            DrawableRequestBuilder<String> builder = Glide.with(context).load(url).placeholder(placeholder);
            if (animation == Animation.NONE)
                builder.dontAnimate();
            else
                builder.crossFade();
            builder.into(imageView);
        }
    }
}
