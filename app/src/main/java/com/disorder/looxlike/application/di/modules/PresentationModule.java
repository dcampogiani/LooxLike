package com.disorder.looxlike.application.di.modules;

import com.disorder.looxlike.application.di.PerActivity;
import com.disorder.networking.services.FakeLooxLikeAPI;
import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.presentation.model.mapper.NewsPostMapper;
import com.disorder.presentation.model.mapper.NewsPostMapperImpl;
import com.disorder.presentation.presenter.GenderNewsPresenterImpl;
import com.disorder.presentation.presenter.HomePresenter;
import com.disorder.presentation.presenter.HomePresenterImpl;
import com.disorder.presentation.presenter.NewsPresenter;
import com.disorder.presentation.utils.MainThreadAndBackgroundRxScheduler;
import com.disorder.presentation.utils.RxScheduler;

import dagger.Module;
import dagger.Provides;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class PresentationModule {

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
    NewsPostMapper provideNewsPostMapper() {
        return new NewsPostMapperImpl();
    }

    @Provides
    @PerActivity
    NewsPresenter provideNewsPresenter(GenderNewsPresenterImpl presenter) {
        return presenter;
    }
}
