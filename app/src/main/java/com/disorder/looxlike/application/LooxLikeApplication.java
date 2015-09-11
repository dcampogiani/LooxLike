package com.disorder.looxlike.application;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.RefWatcher;


public class LooxLikeApplication extends Application {

    private RefWatcher refWatcher;
    private LeakDetector mLeakDetector;

    public static RefWatcher getRefWatcher(Context context) {
        LooxLikeApplication application = (LooxLikeApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initLeakDetector();
    }

    protected void initLeakDetector() {
        new LeakCanaryDetector().init(this);
    }
}
