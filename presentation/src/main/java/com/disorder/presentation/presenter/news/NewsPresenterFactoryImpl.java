package com.disorder.presentation.presenter.news;

import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.presentation.model.mapper.NewsPostMapper;
import com.disorder.presentation.utils.Browser;
import com.disorder.presentation.utils.ItemPageUrlEvaluator;
import com.disorder.presentation.utils.RxScheduler;
import com.disorder.presentation.view.NewsView;

import javax.inject.Inject;

public class NewsPresenterFactoryImpl implements NewsPresenterFactory {

    private final LooxLikeAPI mLooxLikeAPI;
    private final RxScheduler scheduler;
    private final NewsPostMapper mNewsPostMapper;
    private final ItemPageUrlEvaluator mItemPageUrlEvaluator;
    private final Browser mBrowser;

    @Inject
    public NewsPresenterFactoryImpl(LooxLikeAPI mLooxLikeAPI, RxScheduler scheduler, NewsPostMapper mNewsPostMapper, ItemPageUrlEvaluator itemPageUrlEvaluator, Browser browser) {
        this.mLooxLikeAPI = mLooxLikeAPI;
        this.scheduler = scheduler;
        this.mNewsPostMapper = mNewsPostMapper;
        this.mItemPageUrlEvaluator = itemPageUrlEvaluator;
        this.mBrowser = browser;
    }

    @Override
    public NewsPresenter make() {
        return new AllNewsPresenterImpl(mLooxLikeAPI, scheduler, mNewsPostMapper, mItemPageUrlEvaluator, mBrowser);
    }

    @Override
    public NewsPresenter make(@NewsView.Gender int gender) {
        return new GenderNewsPresenterImpl(mLooxLikeAPI, scheduler, mNewsPostMapper, gender, mItemPageUrlEvaluator, mBrowser);
    }
}
