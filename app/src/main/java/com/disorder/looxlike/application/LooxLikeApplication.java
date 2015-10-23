package com.disorder.looxlike.application;

import android.app.Application;
import android.content.Context;

import com.disorder.looxlike.application.di.components.ApplicationComponent;
import com.disorder.looxlike.application.di.components.DaggerApplicationComponent;
import com.disorder.looxlike.application.di.modules.ApplicationModule;
import com.jakewharton.threetenabp.AndroidThreeTen;


public class LooxLikeApplication extends Application {

    private LeakDetector mLeakDetector;
    private ApplicationComponent mApplicationComponent;

    public static ApplicationComponent getApplicationComponent(Context context) {
        LooxLikeApplication application = (LooxLikeApplication) context.getApplicationContext();
        return application.mApplicationComponent;
    }

    public static LeakDetector getLeakDetector(Context context) {
        LooxLikeApplication application = (LooxLikeApplication) context.getApplicationContext();
        return application.mLeakDetector;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initTimeZone();
        initLeakDetector();
        initDagger();
    }

    void initTimeZone() {
        AndroidThreeTen.init(this);
    }

    void initLeakDetector() {
        mLeakDetector = new LeakCanaryDetector(this);
    }

    void initDagger() {
        this.mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }
}
