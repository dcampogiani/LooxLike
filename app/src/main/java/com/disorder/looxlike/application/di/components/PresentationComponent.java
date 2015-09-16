package com.disorder.looxlike.application.di.components;

import com.disorder.looxlike.application.di.PerActivity;
import com.disorder.looxlike.application.di.modules.PresentationModule;
import com.disorder.looxlike.fragments.HomeFragment;
import com.disorder.presentation.presenter.HomePresenter;

import dagger.Component;

@PerActivity
@Component(modules = PresentationModule.class)
public interface PresentationComponent {

    void inject(HomeFragment homeFragment);

    HomePresenter homePresenter();
}
