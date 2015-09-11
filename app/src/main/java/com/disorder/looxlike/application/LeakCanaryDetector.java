package com.disorder.looxlike.application;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by campogianid on 9/11/2015.
 */
public class LeakCanaryDetector implements LeakDetector {

    private RefWatcher mRefWatcher;

    public LeakCanaryDetector(Application application) {
        mRefWatcher = LeakCanary.install(application);
    }

    @Override
    public void watch(Object toBeWatched) {
        mRefWatcher.watch(toBeWatched);
    }
}
