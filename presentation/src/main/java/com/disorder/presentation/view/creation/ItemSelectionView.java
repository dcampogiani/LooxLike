package com.disorder.presentation.view.creation;


public interface ItemSelectionView {

    void showLoading();

    void hideLoading();

    void setModel(String[] items);
}
