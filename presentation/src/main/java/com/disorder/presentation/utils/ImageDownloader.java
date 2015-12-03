package com.disorder.presentation.utils;

import android.widget.ImageView;

public interface ImageDownloader {

    enum Animation {
        NONE, CROSS_FADE
    }

    Request request(String url);

    abstract class Request {
        protected final String url; //required parameters

        protected Animation animation = Animation.NONE; //optional parameters

        public Request(String url) {
            this.url = url;
        }

        public Request withAnimation(Animation animation) {
            this.animation = animation;
            return this;
        }

        public abstract void into(ImageView imageView);
    }
}
