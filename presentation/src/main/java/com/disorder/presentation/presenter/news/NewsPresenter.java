package com.disorder.presentation.presenter.news;

import com.disorder.presentation.presenter.Presenter;
import com.disorder.presentation.view.NewsView;

public interface NewsPresenter extends Presenter<NewsView> {

    void loadMore();

    void showItemInBrowser(String c10);
}
