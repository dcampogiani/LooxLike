package com.disorder.presentation.presenter;

import com.disorder.networking.responses.NewsPost;
import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.presentation.model.mapper.NewsPostMapperImpl;
import com.disorder.presentation.utils.RxScheduler;
import com.disorder.presentation.view.NewsView;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

public class NewsPresenterImpl extends BasePresenter<NewsView> implements NewsPresenter {

    private int currentPage;
    private LooxLikeAPI.Gender gender;
    private LooxLikeAPI mLooxLikeAPI;
    private RxScheduler scheduler;
    private NewsPostMapperImpl mNewsPostMapper;


    @Inject
    public NewsPresenterImpl(LooxLikeAPI mLooxLikeAPI, RxScheduler scheduler, NewsPostMapperImpl newsPostMapper) {
        this.mLooxLikeAPI = mLooxLikeAPI;
        this.scheduler = scheduler;
        this.mNewsPostMapper = newsPostMapper;
        currentPage = 0;
    }

    public void setGender(@NewsView.Gender int gender) {
        this.gender = mapGender(gender);
    }

    @Override
    public void loadMore() {
        getView().showLoading();
        Observable<NewsPost[]> apiObservable = mLooxLikeAPI.getNewsPosts(gender, currentPage);
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

    LooxLikeAPI.Gender mapGender(@NewsView.Gender int gender) {
        if (gender == NewsView.MALE)
            return LooxLikeAPI.Gender.MALE;
        if (gender == NewsView.FEMALE)
            return LooxLikeAPI.Gender.FEMALE;
        else throw new IllegalArgumentException("Gender" + gender + " is not valid");
    }
}
