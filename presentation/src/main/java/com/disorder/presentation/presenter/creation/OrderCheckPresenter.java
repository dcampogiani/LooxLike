package com.disorder.presentation.presenter.creation;


import com.disorder.presentation.presenter.Presenter;
import com.disorder.presentation.view.creation.CheckOrderView;

public interface OrderCheckPresenter extends Presenter<CheckOrderView> {

    void isOrderValid(String orderId);

}
