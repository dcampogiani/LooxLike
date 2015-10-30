package com.disorder.presentation.model;

import android.os.Parcelable;

import auto.parcel.AutoParcel;

@AutoParcel
public abstract class NewsPost implements Parcelable {

    public abstract long id();

    public abstract String description();

    public abstract String photoUrl();

    public abstract String c10();

    public abstract String creation();

    public abstract String username();

    public abstract int likes();

    public abstract boolean liked();


    public static NewsPost create(long id, String description, String photoUrl, String c10, String creation, String username, int likes, boolean liked) {
        return new AutoParcel_NewsPost(id, description, photoUrl, c10, creation, username, likes, liked);
    }

    public static NewsPost updateLike(NewsPost newsPost, boolean liked) {
        int likes = newsPost.likes();
        if (liked)
            likes++;
        else likes--;
        return new AutoParcel_NewsPost(newsPost.id(), newsPost.description(), newsPost.photoUrl(), newsPost.c10(), newsPost.creation(), newsPost.username(), likes, liked);
    }
}
