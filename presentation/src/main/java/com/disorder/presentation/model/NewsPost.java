package com.disorder.presentation.model;

import android.os.Parcelable;

import auto.parcel.AutoParcel;

@AutoParcel
public abstract class NewsPost implements Parcelable {

    public abstract long id();

    public abstract String photoUrl();

    public abstract String description();

    public abstract String itemId();

    public abstract int likes();

    public abstract String username();

    public abstract boolean liked();

    public static NewsPost create(long id, String photoUrl, String description, String itemId, int likes, String username, boolean liked) {
        return new AutoParcel_NewsPost(id, photoUrl, description, itemId, likes, username, liked);
    }
}
