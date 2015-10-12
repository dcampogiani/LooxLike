package com.disorder.looxlike.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.disorder.looxlike.R;
import com.disorder.presentation.model.NewsPost;
import com.disorder.presentation.presenter.NewsPresenter;
import com.disorder.presentation.presenter.news.NewsPresenterFactory;
import com.disorder.presentation.view.NewsView;

import javax.inject.Inject;

public class NewsFragment extends BaseFragment implements NewsView {

    private static final String GENDER_KEY = "GENDER_KEY";


    @Inject
    NewsPresenterFactory mNewsPresenterFactory;

    private NewsPresenter mNewsPresenter;

    public static NewsFragment newInstance() {
        NewsFragment newsFragment = new NewsFragment();
        Bundle args = new Bundle();
        newsFragment.setArguments(args);
        return newsFragment;
    }

    public static NewsFragment newInstance(@NewsView.Gender int gender) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putInt(GENDER_KEY, gender);
        newsFragment.setArguments(args);
        return newsFragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getPresentationComponent().inject(this);
        Bundle arguments = getArguments();
        @NewsView.Gender int gender = arguments.getInt(GENDER_KEY, -1);
        if (gender >= 0)
            mNewsPresenter = mNewsPresenterFactory.make(gender);
        else mNewsPresenter = mNewsPresenterFactory.make();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
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
