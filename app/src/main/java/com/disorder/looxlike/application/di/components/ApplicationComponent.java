package com.disorder.looxlike.application.di.components;

import com.disorder.looxlike.application.di.PerApplication;
import com.disorder.looxlike.application.di.modules.ApplicationModule;
import com.disorder.presentation.utils.ImageDownloader;

import dagger.Component;

@PerApplication
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    ImageDownloader imageDownloader();
}
