package com.disorder.looxlike.application.di.modules;

import android.content.Context;

import com.disorder.looxlike.application.di.PerActivity;
import com.disorder.networking.services.FakeLooxLikeAPI;
import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.presentation.model.mapper.NewsPostMapper;
import com.disorder.presentation.model.mapper.NewsPostMapperImpl;
import com.disorder.presentation.presenter.HomePresenter;
import com.disorder.presentation.presenter.HomePresenterImpl;
import com.disorder.presentation.presenter.news.NewsPresenterFactory;
import com.disorder.presentation.presenter.news.NewsPresenterFactoryImpl;
import com.disorder.presentation.utils.DateIntervalCalculator;
import com.disorder.presentation.utils.DateIntervalCalculatorImpl;
import com.disorder.presentation.utils.DaysRangeProvider;
import com.disorder.presentation.utils.DaysRangeProviderImpl;
import com.disorder.presentation.utils.MainThreadAndBackgroundRxScheduler;
import com.disorder.presentation.utils.RxScheduler;

import dagger.Module;
import dagger.Provides;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class PresentationModule {

    private final Context context;

    public PresentationModule(Context context) {
        this.context = context;
    }

    @Provides
    @PerActivity
    Context provideContext() {
        return context;
    }

    @Provides
    @PerActivity
    HomePresenter provideHomePresenter(HomePresenterImpl homePresenter) {
        return homePresenter;
    }

    @Provides
    @PerActivity
    LooxLikeAPI provideLooxLikeAPI() {
        //TODO switch to real implementation when the communication with server is ready
        return new FakeLooxLikeAPI();
    }


    @Provides
    @PerActivity
    RxScheduler provideRxScheduler() {
        return new MainThreadAndBackgroundRxScheduler(AndroidSchedulers.mainThread(), Schedulers.io());
    }

    @Provides
    @PerActivity
    DateIntervalCalculator provideDateIntervalCalculator(DateIntervalCalculatorImpl dateIntervalCalculator) {
        return dateIntervalCalculator;
    }

    @Provides
    @PerActivity
    DaysRangeProvider provideDaysRangeProvider(DaysRangeProviderImpl daysRangeProvider) {
        return daysRangeProvider;
    }

    @Provides
    @PerActivity
    NewsPostMapper provideNewsPostMapper(NewsPostMapperImpl mapper) {
        return mapper;
    }

    @Provides
    @PerActivity
    NewsPresenterFactory provideNewsPresenterFactory(NewsPresenterFactoryImpl factory) {
        return factory;
    }
}
