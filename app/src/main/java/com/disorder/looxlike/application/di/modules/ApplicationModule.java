package com.disorder.looxlike.application.di.modules;


import com.disorder.looxlike.application.LooxLikeApplication;
import com.disorder.looxlike.application.di.PerApplication;
import com.disorder.presentation.utils.GlideImageDownloader;
import com.disorder.presentation.utils.ImageDownloader;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final LooxLikeApplication application;

    public ApplicationModule(LooxLikeApplication application) {
        this.application = application;
    }

    @Provides
    @PerApplication
    ImageDownloader provideImageDownloader() {
        return new GlideImageDownloader(application);
    }
}
