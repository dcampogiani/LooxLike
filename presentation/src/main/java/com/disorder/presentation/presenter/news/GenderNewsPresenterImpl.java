package com.disorder.presentation.presenter.news;

import com.disorder.networking.responses.NewsPost;
import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.presentation.model.mapper.NewsPostMapper;
import com.disorder.presentation.utils.Browser;
import com.disorder.presentation.utils.ItemPageUrlEvaluator;
import com.disorder.presentation.utils.RxScheduler;
import com.disorder.presentation.view.NewsView;

import rx.Observable;

public class GenderNewsPresenterImpl extends BaseNewsPresenter {

    private final LooxLikeAPI.Gender gender;

    public GenderNewsPresenterImpl(LooxLikeAPI mLooxLikeAPI, RxScheduler scheduler, NewsPostMapper newsPostMapper, @NewsView.Gender int gender, ItemPageUrlEvaluator itemPageUrlEvaluator, Browser browser) {
        super(mLooxLikeAPI, scheduler, newsPostMapper, itemPageUrlEvaluator, browser);
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
        if (gender == NewsView.NO_GENDER)
            return LooxLikeAPI.Gender.NOGENDER;
        else throw new IllegalArgumentException("Gender " + gender + " is not valid");
    }
}
