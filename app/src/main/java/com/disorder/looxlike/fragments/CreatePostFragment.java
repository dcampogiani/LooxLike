package com.disorder.looxlike.fragments;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.disorder.looxlike.R;
import com.disorder.presentation.view.creation.CreatePostView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreatePostFragment extends BaseFragment implements CreatePostView, CheckFragmentOrder.OnOrderValidListener, ItemSelectionFragment.OnItemSelectedListener, CreatePostConfirmationFragment.OnConfirmationListener {

    private static final int fragment_create_container = R.id.fragment_create_container;

    @Bind(fragment_create_container)
    CoordinatorLayout mCoordinatorLayout;

    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;

    private String c10;
    private String photoFilePath;


    public interface OnTakePictureRequestListener {
        void onTakePictureRequest();
    }


    public static CreatePostFragment newInstance() {
        return new CreatePostFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create, container, false);
        ButterKnife.bind(this, root);
        if (savedInstanceState == null)
            getChildFragmentManager().beginTransaction().replace(fragment_create_container, CheckFragmentOrder.newInstance()).commit();
        return root;
    }

    @Override
    public void onOrderValid(String orderId) {
        getChildFragmentManager().beginTransaction().replace(fragment_create_container, ItemSelectionFragment.newInstance(orderId)).addToBackStack(null).commit();
    }

    @Override
    public void onItemSelected(String item) {
        c10 = item;

        OnTakePictureRequestListener onTakePictureRequestListener;

        try {
            onTakePictureRequestListener = (OnTakePictureRequestListener) getActivity();
            onTakePictureRequestListener.onTakePictureRequest();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnTakePictureRequestListener");
        }

    }

    @Override
    public void onConfirmation(String description) {
        Snackbar.make(mCoordinatorLayout, description, Snackbar.LENGTH_INDEFINITE).show();
    }

    public void setPhotoFilePath(String photoFilePath) {
        this.photoFilePath = photoFilePath;
        Toast.makeText(getContext(), photoFilePath, Toast.LENGTH_LONG).show();
    }


    @Override
    public void showLoading() {
        mProgressBar.animate().alpha(1).setInterpolator(new AccelerateDecelerateInterpolator());
        mCoordinatorLayout.animate().alpha(0.3f).setInterpolator(new AccelerateDecelerateInterpolator());
    }

    @Override
    public void hideLoading() {
        mProgressBar.animate().alpha(0).setInterpolator(new AccelerateDecelerateInterpolator());
        mCoordinatorLayout.animate().alpha(1).setInterpolator(new AccelerateDecelerateInterpolator());
    }

    @Override
    public void postCreated() {
        Snackbar.make(mCoordinatorLayout, "Uploaded", Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void showError() {
        Snackbar.make(mCoordinatorLayout, getString(R.string.generic_error), Snackbar.LENGTH_INDEFINITE).show();
    }
}
