package com.disorder.presentation.view.creation;

public interface CheckOrderView {

    void orderIsValid();

    void orderIsNotValid();

    void showLoading();

    void hideLoading();
}
