package com.disorder.presentation.presenter;

import com.disorder.networking.responses.NewsPost;
import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.presentation.model.mapper.NewsPostMapper;
import com.disorder.presentation.utils.RxScheduler;
import com.disorder.presentation.view.NewsView;

import javax.inject.Inject;

import rx.Observable;

public class GenderNewsPresenterImpl extends AbstractNewsPresenter {

    private LooxLikeAPI.Gender gender;

    @Inject
    public GenderNewsPresenterImpl(LooxLikeAPI mLooxLikeAPI, RxScheduler scheduler, NewsPostMapper newsPostMapper) {
        super(mLooxLikeAPI, scheduler, newsPostMapper);
    }

    public void setGender(@NewsView.Gender int gender) {
        this.gender = mapGender(gender);
    }

    @Override
    protected void validateParameters() {
        if (gender == null)
            throw new IllegalStateException();
    }

    @Override
    protected Observable<NewsPost[]> fetchData() {
        return mLooxLikeAPI.getNewsPosts(gender, currentPage);
    }

    private LooxLikeAPI.Gender mapGender(@NewsView.Gender int gender) {
        if (gender == NewsView.MALE)
            return LooxLikeAPI.Gender.MALE;
        if (gender == NewsView.FEMALE)
            return LooxLikeAPI.Gender.FEMALE;
        else throw new IllegalArgumentException("Gender" + gender + " is not valid");
    }
}
