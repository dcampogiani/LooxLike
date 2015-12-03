package com.disorder.presentation.presenter.news;


import com.disorder.presentation.view.NewsView;

public interface NewsPresenterFactory {

    NewsPresenter make();

    NewsPresenter make(@NewsView.Gender int gender);
}
