package com.disorder.presentation.presenter.creation;


import com.disorder.networking.requests.CreatePostRequest;
import com.disorder.networking.responses.NewsPost;
import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.presentation.presenter.BasePresenter;
import com.disorder.presentation.utils.RxScheduler;
import com.disorder.presentation.view.creation.CreatePostView;

import java.io.File;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

public class CreatePostPresenterImpl extends BasePresenter<CreatePostView> implements CreatePostPresenter {

    private final LooxLikeAPI mLooxLikeAPI;
    private final RxScheduler scheduler;

    @Inject
    public CreatePostPresenterImpl(LooxLikeAPI looxLikeAPI, RxScheduler rxScheduler) {
        this.mLooxLikeAPI = looxLikeAPI;
        this.scheduler = rxScheduler;
    }

    @Override
    public void createPost(String c10, String description, String photoFilePath) {

        getView().showLoading();
        File file = new File(photoFilePath);
        CreatePostRequest request = new CreatePostRequest(description, c10, file);

        Observable<NewsPost> apiObservable = mLooxLikeAPI.createPost(request);
        Observable<NewsPost> scheduledObservable = scheduler.schedule(apiObservable);
        scheduledObservable.subscribe(new Action1<NewsPost>() {
            @Override
            public void call(NewsPost newsPost) {
                CreatePostView view = getView();
                if (view != null) {
                    view.hideLoading();
                    view.postCreated();
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                CreatePostView view = getView();
                if (view != null) {
                    view.hideLoading();
                    view.showError();
                }
            }
        });
    }
}
