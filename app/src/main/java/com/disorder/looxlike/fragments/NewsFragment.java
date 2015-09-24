package com.disorder.looxlike.fragments;


import android.os.Bundle;

import com.disorder.presentation.model.NewsPost;
import com.disorder.presentation.presenter.NewsPresenter;
import com.disorder.presentation.view.NewsView;

import javax.inject.Inject;

public class NewsFragment extends BaseFragment implements NewsView {

    @Inject
    NewsPresenter mNewsPresenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getPresentationComponent().inject(this);
    }

    @Override
    public void updateModel(NewsPost[] model) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showLoading() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void hideLoading() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showError(String errorMessage) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void hideError() {
        throw new UnsupportedOperationException();
    }
}
