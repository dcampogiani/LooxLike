package com.disorder.looxlike.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.disorder.looxlike.R;
import com.disorder.presentation.presenter.creation.OrderCheckPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CheckOrderFragment extends BaseFragment {

    @Bind(R.id.orderIdWrapper)
    TextInputLayout mTextInputLayout;

    @Bind(R.id.orderId)
    EditText mEditText;

    @Inject
    OrderCheckPresenter mOrderCheckPresenter;

    public static CheckOrderFragment newInstance() {
        return new CheckOrderFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getPresentationComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_check_order, container, false);
        ButterKnife.bind(this, root);
        return root;
    }
}
