package com.disorder.presentation.presenter;

import com.disorder.presentation.view.ToolbarView;

public interface ToolbarPresenter extends Presenter<ToolbarView> {

    void onNewsButtonClick();

    void onUserButtonClick();

    void onFavouritesButtonClick();

}
