package com.disorder.presentation.presenter;


import com.disorder.presentation.view.HomeView;

public class HomePresenterImpl extends BasePresenter<HomeView> implements HomePresenter {

    @Override
    public void onNewsButtonClick() {
        getView().showPage(HomeView.NEWS);
    }

    @Override
    public void onUserButtonClick() {
        getView().showPage(HomeView.USER);
    }

    @Override
    public void onFavouriteButtonClick() {
        getView().showPage(HomeView.FAVOURITES);
    }
}
