package com.disorder.presentation.presenter;


import com.disorder.presentation.view.ToolbarView;

import javax.inject.Inject;

public class ToolbarPresenterImpl extends BasePresenter<ToolbarView> implements ToolbarPresenter {

    @Inject
    public ToolbarPresenterImpl() {
    }

    @Override
    public void onNewsButtonClick() {
        getView().showPage(ToolbarView.NEWS);
    }

    @Override
    public void onUserButtonClick() {
        getView().showPage(ToolbarView.USER);
    }

    @Override
    public void onFavouritesButtonClick() {
        getView().showPage(ToolbarView.FAVOURITES);
    }
}
