package com.disorder.presentation.presenter.news;

import com.disorder.networking.responses.NewsPost;
import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.presentation.model.mapper.NewsPostMapper;
import com.disorder.presentation.presenter.BasePresenter;
import com.disorder.presentation.utils.Browser;
import com.disorder.presentation.utils.ItemPageUrlEvaluator;
import com.disorder.presentation.utils.RxScheduler;
import com.disorder.presentation.view.NewsView;

import rx.Observable;
import rx.functions.Action1;

public abstract class BaseNewsPresenter extends BasePresenter<NewsView> implements NewsPresenter {

    int currentPage;
    final LooxLikeAPI mLooxLikeAPI;
    private final RxScheduler scheduler;
    private final NewsPostMapper mNewsPostMapper;
    private final ItemPageUrlEvaluator mItemPageUrlEvaluator;
    private final Browser mBrowser;

    BaseNewsPresenter(LooxLikeAPI mLooxLikeAPI, RxScheduler scheduler, NewsPostMapper newsPostMapper, ItemPageUrlEvaluator itemPageUrlEvaluator, Browser browser) {
        this.mLooxLikeAPI = mLooxLikeAPI;
        this.scheduler = scheduler;
        this.mNewsPostMapper = newsPostMapper;
        this.mBrowser = browser;
        this.mItemPageUrlEvaluator = itemPageUrlEvaluator;
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
                currentPage++;

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                NewsView view = getView();
                if (view != null) {
                    view.hideLoading();
                    view.hideError();
                    view.showError(throwable.toString());
                }
            }
        });

    }

    @Override
    public void showItemInBrowser(String c10) {
        //TODO compute c10 url
        mBrowser.navigateTo(mItemPageUrlEvaluator.evaluate(c10));
    }

    protected abstract void validateParameters();


    protected abstract Observable<NewsPost[]> fetchData();
}
