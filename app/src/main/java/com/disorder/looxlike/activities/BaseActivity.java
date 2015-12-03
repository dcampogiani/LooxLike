package com.disorder.looxlike.activities;


import android.support.v7.app.AppCompatActivity;

import com.disorder.looxlike.application.LooxLikeApplication;
import com.disorder.looxlike.application.di.components.ApplicationComponent;
import com.disorder.looxlike.application.di.components.DaggerPresentationComponent;
import com.disorder.looxlike.application.di.components.PresentationComponent;
import com.disorder.looxlike.application.di.modules.PresentationModule;

public abstract class BaseActivity extends AppCompatActivity {

    private PresentationComponent mPresentationComponent;

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
