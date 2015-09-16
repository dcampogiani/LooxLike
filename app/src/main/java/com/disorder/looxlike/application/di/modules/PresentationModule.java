package com.disorder.looxlike.application.di.modules;

import com.disorder.looxlike.application.di.PerActivity;
import com.disorder.presentation.presenter.HomePresenter;
import com.disorder.presentation.presenter.HomePresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {

    @Provides
    @PerActivity
    HomePresenter provideHomePresenter(HomePresenterImpl homePresenter) {
        return homePresenter;
    }
}
