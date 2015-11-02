package com.disorder.looxlike.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.disorder.looxlike.application.LooxLikeApplication;
import com.disorder.looxlike.application.di.components.ApplicationComponent;
import com.disorder.looxlike.application.di.components.DaggerPresentationComponent;
import com.disorder.looxlike.application.di.components.PresentationComponent;
import com.disorder.looxlike.application.di.modules.PresentationModule;

import icepick.Icepick;

public abstract class BaseActivity extends AppCompatActivity {

    private PresentationComponent mPresentationComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    public PresentationComponent getPresentationComponent() {
        if (mPresentationComponent == null) {
            ApplicationComponent applicationComponent = LooxLikeApplication.getApplicationComponent(this);
            PresentationModule presentationModule = new PresentationModule(this);
            mPresentationComponent = DaggerPresentationComponent.builder()
                    .applicationComponent(applicationComponent)
                    .presentationModule(presentationModule)
                    .build();
        }
        return mPresentationComponent;
    }
}
