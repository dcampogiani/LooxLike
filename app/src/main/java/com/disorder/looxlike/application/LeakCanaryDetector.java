package com.disorder.looxlike.application;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class LeakCanaryDetector implements LeakDetector {

    private final RefWatcher mRefWatcher;

    public LeakCanaryDetector(Application application) {
        mRefWatcher = LeakCanary.install(application);
    }

    @Override
    public void watch(Object toBeWatched) {
        mRefWatcher.watch(toBeWatched);
    }
}
