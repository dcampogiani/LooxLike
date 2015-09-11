package com.disorder.looxlike.application;

import android.app.Application;

/**
 * Created by campogianid on 9/11/2015.
 */
public interface LeakDetector {

    void init(Application application);
}
