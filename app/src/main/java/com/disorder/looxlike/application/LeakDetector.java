package com.disorder.looxlike.application;

public interface LeakDetector {

    void watch(Object toBeWatched);
}
