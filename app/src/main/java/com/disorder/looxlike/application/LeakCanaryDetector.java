package com.disorder.looxlike.application;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by campogianid on 9/11/2015.
 */
public class LeakCanaryDetector implements LeakDetector {

    @Override
    public void init(Application application) {
        LeakCanary.install(application);
    }
}
