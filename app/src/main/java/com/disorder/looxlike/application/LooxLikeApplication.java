package com.disorder.looxlike.application;

import android.app.Application;
import android.content.Context;


public class LooxLikeApplication extends Application {

    private LeakDetector mLeakDetector;

    public static LeakDetector getLeakDetector(Context context) {
        LooxLikeApplication application = (LooxLikeApplication) context.getApplicationContext();
        return application.mLeakDetector;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initLeakDetector();
    }

    void initLeakDetector() {
        mLeakDetector = new LeakCanaryDetector(this);
    }
}
