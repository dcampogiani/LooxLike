package com.disorder.presentation.presenter;

import com.disorder.presentation.view.HomeView;

public interface HomePresenter extends Presenter<HomeView> {

    void onNewsButtonClick();

    void onUserButtonClick();

    void onFavouritesButtonClick();

}
