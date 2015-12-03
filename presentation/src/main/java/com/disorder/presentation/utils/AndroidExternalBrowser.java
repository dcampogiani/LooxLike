package com.disorder.presentation.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import javax.inject.Inject;

public class AndroidExternalBrowser implements Browser {

    private final Context mContext;

    @Inject
    public AndroidExternalBrowser(Context mContext) {
        this.mContext = mContext.getApplicationContext();
    }

    @Override
    public void navigateTo(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            mContext.startActivity(intent);
        }
    }
}
