package com.disorder.presentation.presenter;

import com.disorder.networking.responses.NewsPost;
import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.presentation.model.mapper.NewsPostMapper;
import com.disorder.presentation.utils.RxScheduler;

import javax.inject.Inject;

import rx.Observable;

public class AllNewsPresenterImpl extends AbstractNewsPresenter {

    @Inject
    public AllNewsPresenterImpl(LooxLikeAPI mLooxLikeAPI, RxScheduler scheduler, NewsPostMapper newsPostMapper) {
        super(mLooxLikeAPI, scheduler, newsPostMapper);
    }

    @Override
    protected void validateParameters() {
    }

    @Override
    protected Observable<NewsPost[]> fetchData() {
        return mLooxLikeAPI.getNewsPosts(currentPage);
    }
}
