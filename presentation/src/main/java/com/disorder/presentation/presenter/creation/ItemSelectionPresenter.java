package com.disorder.presentation.presenter.creation;

import com.disorder.presentation.presenter.Presenter;
import com.disorder.presentation.view.creation.ItemSelectionView;

public interface ItemSelectionPresenter extends Presenter<ItemSelectionView> {

    void loadItemsForOrder(String orderId);
}
