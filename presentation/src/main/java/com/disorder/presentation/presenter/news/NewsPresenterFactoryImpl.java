package com.disorder.presentation.presenter.news;

import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.presentation.model.mapper.NewsPostMapper;
import com.disorder.presentation.utils.RxScheduler;
import com.disorder.presentation.view.NewsView;

import javax.inject.Inject;

public class NewsPresenterFactoryImpl implements NewsPresenterFactory {

    private LooxLikeAPI mLooxLikeAPI;
    private RxScheduler scheduler;
    private NewsPostMapper mNewsPostMapper;

    @Inject
    public NewsPresenterFactoryImpl(LooxLikeAPI mLooxLikeAPI, RxScheduler scheduler, NewsPostMapper mNewsPostMapper) {
        this.mLooxLikeAPI = mLooxLikeAPI;
        this.scheduler = scheduler;
        this.mNewsPostMapper = mNewsPostMapper;
    }

    @Override
    public NewsPresenter make() {
        return new AllNewsPresenterImpl(mLooxLikeAPI, scheduler, mNewsPostMapper);
    }

    @Override
    public NewsPresenter make(@NewsView.Gender int gender) {
        return new GenderNewsPresenterImpl(mLooxLikeAPI, scheduler, mNewsPostMapper, gender);
    }
}
