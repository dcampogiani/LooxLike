package com.disorder.looxlike.application.di.components;

import android.content.Context;

import com.disorder.looxlike.application.di.PerActivity;
import com.disorder.looxlike.application.di.modules.PresentationModule;
import com.disorder.looxlike.fragments.CheckOrderFragment;
import com.disorder.looxlike.fragments.NewsFragment;
import com.disorder.looxlike.fragments.ToolbarFragment;
import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.presentation.model.mapper.NewsPostMapper;
import com.disorder.presentation.presenter.ToolbarPresenter;
import com.disorder.presentation.presenter.creation.OrderCheckPresenter;
import com.disorder.presentation.utils.Browser;
import com.disorder.presentation.utils.ItemPageUrlEvaluator;
import com.disorder.presentation.utils.RxScheduler;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = PresentationModule.class)
public interface PresentationComponent {

    void inject(ToolbarFragment toolbarFragment);

    void inject(NewsFragment newsFragment);

    void inject(CheckOrderFragment checkOrderFragment);

    Context context();

    ToolbarPresenter homePresenter();

    LooxLikeAPI looxLikeAPI();

    RxScheduler rxScheduler();

    NewsPostMapper newsPostMapper();

    ItemPageUrlEvaluator itemPageUrlEvaluator();

    Browser browser();

    OrderCheckPresenter orderCheckPresenter();
}
