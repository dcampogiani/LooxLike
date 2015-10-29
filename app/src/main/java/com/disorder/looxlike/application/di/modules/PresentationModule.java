package com.disorder.looxlike.application.di.modules;

import android.content.Context;

import com.disorder.looxlike.BuildConfig;
import com.disorder.looxlike.application.di.PerActivity;
import com.disorder.networking.authorization.Authorization;
import com.disorder.networking.authorization.BasicAuthorization;
import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.networking.services.retrofit.RetrofitLooxLikeAPI;
import com.disorder.networking.utils.ApacheBase64Encoder;
import com.disorder.networking.utils.Base64Encoder;
import com.disorder.presentation.model.mapper.NewsPostMapper;
import com.disorder.presentation.model.mapper.NewsPostMapperImpl;
import com.disorder.presentation.presenter.ToolbarPresenter;
import com.disorder.presentation.presenter.ToolbarPresenterImpl;
import com.disorder.presentation.presenter.creation.OrderCheckPresenter;
import com.disorder.presentation.presenter.creation.OrderCheckPresenterImpl;
import com.disorder.presentation.presenter.news.NewsPresenterFactory;
import com.disorder.presentation.presenter.news.NewsPresenterFactoryImpl;
import com.disorder.presentation.utils.AndroidExternalBrowser;
import com.disorder.presentation.utils.Browser;
import com.disorder.presentation.utils.CountryItemPageEvaluatorImpl;
import com.disorder.presentation.utils.DateIntervalCalculator;
import com.disorder.presentation.utils.DateIntervalCalculatorImpl;
import com.disorder.presentation.utils.DaysRangeProvider;
import com.disorder.presentation.utils.DaysRangeProviderImpl;
import com.disorder.presentation.utils.ItemPageUrlEvaluator;
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
    ToolbarPresenter provideHomePresenter(ToolbarPresenterImpl homePresenter) {
        return homePresenter;
    }

    @Provides
    @PerActivity
    LooxLikeAPI provideLooxLikeAPI() {
        Base64Encoder encoder = new ApacheBase64Encoder();
        Authorization authorization = new BasicAuthorization("daniele", "password", encoder);
        return new RetrofitLooxLikeAPI(BuildConfig.API_BASE_URL, authorization);
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

    @Provides
    @PerActivity
    ItemPageUrlEvaluator provideItemPageUrlEvaluator() {
        return new CountryItemPageEvaluatorImpl(BuildConfig.Default_Country_Code);
    }

    @Provides
    @PerActivity
    Browser provideBrowser(AndroidExternalBrowser browser) {
        return browser;
    }

    @Provides
    @PerActivity
    OrderCheckPresenter provideOrderCheckPresenter(OrderCheckPresenterImpl orderCheckPresenter) {
        return orderCheckPresenter;
    }
}
