package com.disorder.presentation.presenter.news;

import com.disorder.networking.responses.NewsPost;
import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.presentation.model.mapper.NewsPostMapper;
import com.disorder.presentation.presenter.BasePresenter;
import com.disorder.presentation.presenter.NewsPresenter;
import com.disorder.presentation.utils.RxScheduler;
import com.disorder.presentation.view.NewsView;

import rx.Observable;
import rx.functions.Action1;

public abstract class BaseNewsPresenter extends BasePresenter<NewsView> implements NewsPresenter {

    protected int currentPage;
    protected LooxLikeAPI mLooxLikeAPI;
    private RxScheduler scheduler;
    private NewsPostMapper mNewsPostMapper;

    public BaseNewsPresenter(LooxLikeAPI mLooxLikeAPI, RxScheduler scheduler, NewsPostMapper newsPostMapper) {
        this.mLooxLikeAPI = mLooxLikeAPI;
        this.scheduler = scheduler;
        this.mNewsPostMapper = newsPostMapper;
        this.currentPage = 0;
    }

    @Override
    public void loadMore() {
        validateParameters();
        getView().showLoading();
        Observable<NewsPost[]> apiObservable = fetchData();
        Observable<NewsPost[]> scheduledObservable = scheduler.schedule(apiObservable);
        scheduledObservable.subscribe(new Action1<NewsPost[]>() {
            @Override
            public void call(NewsPost[] newsPosts) {
                com.disorder.presentation.model.NewsPost[] presentationModel = mNewsPostMapper.map(newsPosts);
                getView().hideLoading();
                getView().hideError();
                getView().updateModel(presentationModel);

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                getView().hideLoading();
                getView().hideError();
                getView().showError(throwable.toString());
            }
        });

    }

    protected abstract void validateParameters();


    protected abstract Observable<NewsPost[]> fetchData();
}
