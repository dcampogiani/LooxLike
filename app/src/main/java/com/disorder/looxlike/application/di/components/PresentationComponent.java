package com.disorder.looxlike.application.di.components;

import android.content.Context;

import com.disorder.looxlike.application.di.PerActivity;
import com.disorder.looxlike.application.di.modules.PresentationModule;
import com.disorder.looxlike.fragments.HomeFragment;
import com.disorder.looxlike.fragments.NewsFragment;
import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.presentation.model.mapper.NewsPostMapper;
import com.disorder.presentation.presenter.HomePresenter;
import com.disorder.presentation.utils.RxScheduler;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = PresentationModule.class)
public interface PresentationComponent {

    void inject(HomeFragment homeFragment);

    void inject(NewsFragment newsFragment);

    Context context();

    HomePresenter homePresenter();

    LooxLikeAPI looxLikeAPI();

    RxScheduler rxScheduler();

    NewsPostMapper newsPostMapper();
}
