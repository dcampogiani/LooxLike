package com.disorder.looxlike.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.disorder.looxlike.R;
import com.disorder.presentation.presenter.creation.OrderCheckPresenter;
import com.disorder.presentation.view.creation.CheckOrderView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CheckFragmentOrder extends BaseFragment implements CheckOrderView {

    public interface OnOrderValidListener {
        void onOrderValid(String orderId);
    }

    @Bind(R.id.orderIdWrapper)
    TextInputLayout mTextInputLayout;

    @Bind(R.id.orderId)
    EditText mEditText;

    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;

    @Inject
    OrderCheckPresenter mOrderCheckPresenter;

    private TextWatcher mTextWatcher;

    public static CheckFragmentOrder newInstance() {
        return new CheckFragmentOrder();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_check_order, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresentationComponent().inject(this);
        mOrderCheckPresenter.attachView(this);
        mTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int len = s.length();
                if (len == 14) {
                    hideKeyboard();
                    mOrderCheckPresenter.isOrderValid(s.toString());
                }
            }
        };
        mEditText.addTextChangedListener(mTextWatcher);
    }

    @Override
    public void onDestroy() {
        mEditText.removeTextChangedListener(mTextWatcher);
        super.onDestroy();
    }

    @Override
    public void orderIsValid() {
        mTextInputLayout.setErrorEnabled(false);

        OnOrderValidListener onOrderValidListener;
        try {
            onOrderValidListener = (OnOrderValidListener) getParentFragment();
            onOrderValidListener.onOrderValid(mEditText.getText().toString());
        } catch (ClassCastException e) {
            throw new ClassCastException(getParentFragment().toString()
                    + " must implement OnOrderValidListener");
        }


    }

    @Override
    public void orderIsNotValid() {
        String errorMessage = getString(R.string.generic_error);
        mTextInputLayout.setError(errorMessage);
    }

    @Override
    public void showLoading() {
        mProgressBar.animate().alpha(1).setInterpolator(new AccelerateDecelerateInterpolator());
        mTextInputLayout.animate().alpha(0.3f).setInterpolator(new AccelerateDecelerateInterpolator());
        mEditText.animate().alpha(0.3f).setInterpolator(new AccelerateDecelerateInterpolator());
    }

    @Override
    public void hideLoading() {
        mProgressBar.animate().alpha(0).setInterpolator(new AccelerateDecelerateInterpolator());
        mTextInputLayout.animate().alpha(1).setInterpolator(new AccelerateDecelerateInterpolator());
        mEditText.animate().alpha(1).setInterpolator(new AccelerateDecelerateInterpolator());
    }

    private void hideKeyboard() {
        FragmentActivity activity = getActivity();
        View view = activity.getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
