package com.disorder.presentation.presenter.creation;


import com.disorder.presentation.presenter.Presenter;
import com.disorder.presentation.view.creation.OrderCheckView;

public interface OrderCheckPresenter extends Presenter<OrderCheckView> {

    void isOrderValid(String orderId);

}
