package com.disorder.presentation.presenter.news;


import com.disorder.networking.responses.NewsPost;
import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.presentation.model.mapper.NewsPostMapper;
import com.disorder.presentation.utils.Browser;
import com.disorder.presentation.utils.ItemPageUrlEvaluator;
import com.disorder.presentation.utils.RxScheduler;

import javax.inject.Inject;

import rx.Observable;

public class LikedPostPresenter extends BaseNewsPresenter {

    @Inject
    public LikedPostPresenter(LooxLikeAPI mLooxLikeAPI, RxScheduler scheduler, NewsPostMapper newsPostMapper, ItemPageUrlEvaluator itemPageUrlEvaluator, Browser browser) {
        super(mLooxLikeAPI, scheduler, newsPostMapper, itemPageUrlEvaluator, browser);
    }

    @Override
    protected void validateParameters() {
        return;
    }

    @Override
    protected Observable<NewsPost[]> fetchData() {
        return mLooxLikeAPI.getLikedPosts(currentPage);
    }
}
