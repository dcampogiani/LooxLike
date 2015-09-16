package com.disorder.presentation.presenter;


import com.disorder.presentation.view.HomeView;

import javax.inject.Inject;

public class HomePresenterImpl extends BasePresenter<HomeView> implements HomePresenter {

    @Inject
    public HomePresenterImpl() {
    }

    @Override
    public void onNewsButtonClick() {
        getView().showPage(HomeView.NEWS);
    }

    @Override
    public void onUserButtonClick() {
        getView().showPage(HomeView.USER);
    }

    @Override
    public void onFavouritesButtonClick() {
        getView().showPage(HomeView.FAVOURITES);
    }
}
