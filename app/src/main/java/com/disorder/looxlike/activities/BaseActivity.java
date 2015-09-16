package com.disorder.looxlike.activities;


import android.support.v7.app.AppCompatActivity;

import com.disorder.looxlike.application.di.components.DaggerPresentationComponent;
import com.disorder.looxlike.application.di.components.PresentationComponent;

public abstract class BaseActivity extends AppCompatActivity {

    private PresentationComponent mPresentationComponent;

    public PresentationComponent getPresentationComponent() {
        if (mPresentationComponent == null)
            mPresentationComponent = DaggerPresentationComponent.create();
        return mPresentationComponent;
    }
}
