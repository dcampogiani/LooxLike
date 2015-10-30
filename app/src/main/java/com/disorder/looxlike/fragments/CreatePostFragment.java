package com.disorder.looxlike.fragments;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;

import com.disorder.looxlike.R;
import com.disorder.presentation.presenter.creation.CreatePostPresenter;
import com.disorder.presentation.view.creation.CreatePostView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreatePostFragment extends BaseFragment implements CreatePostView, CheckFragmentOrder.OnOrderValidListener, ItemSelectionFragment.OnItemSelectedListener, CreatePostConfirmationFragment.OnConfirmationListener {

    public static final int fragment_create_container = R.id.fragment_create_container;

    private static final String C10_KEY = "C10_KEY";
    private static final String PHOTO_FILE_PATH_KEY = "PHOTO_FILE_PATH_KEY";

    @Bind(fragment_create_container)
    CoordinatorLayout mCoordinatorLayout;

    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;

    @Inject
    CreatePostPresenter mCreatePostPresenter;

    private String c10;
    private String photoFilePath;


    public interface OnTakePictureRequestListener {
        void onTakePictureRequest();
    }

    public interface Onc10SelectedListener {
        void onc10Selected(String c10);
    }

    public static CreatePostFragment newInstance(String c10, String photoFilePath) {
        CreatePostFragment result = new CreatePostFragment();
        Bundle bundle = new Bundle();
        bundle.putString(C10_KEY, c10);
        bundle.putString(PHOTO_FILE_PATH_KEY, photoFilePath);
        result.setArguments(bundle);
        return result;
    }

    public static CreatePostFragment newInstance() {
        CreatePostFragment result = new CreatePostFragment();
        result.setArguments(new Bundle());
        return result;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create, container, false);
        ButterKnife.bind(this, root);
        Bundle args = getArguments();
        photoFilePath = args.getString(PHOTO_FILE_PATH_KEY);
        c10 = args.getString(C10_KEY);
        if (photoFilePath != null) {
            getChildFragmentManager().beginTransaction().replace(fragment_create_container, CreatePostConfirmationFragment.newInstance(photoFilePath)).commit();
        } else if (savedInstanceState == null) {
            getChildFragmentManager().beginTransaction().replace(fragment_create_container, CheckFragmentOrder.newInstance()).commit();
        }
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresentationComponent().inject(this);
        mCreatePostPresenter.attachView(this);
    }

    @Override
    public void onOrderValid(String orderId) {
        getChildFragmentManager().beginTransaction().replace(fragment_create_container, ItemSelectionFragment.newInstance(orderId)).addToBackStack(null).commit();
    }

    @Override
    public void onItemSelected(String item) {
        c10 = item;

        OnTakePictureRequestListener onTakePictureRequestListener;
        Onc10SelectedListener onc10SelectedListener;

        try {
            onTakePictureRequestListener = (OnTakePictureRequestListener) getActivity();
            onTakePictureRequestListener.onTakePictureRequest();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnTakePictureRequestListener");
        }

        try {
            onc10SelectedListener = (Onc10SelectedListener) getActivity();
            onc10SelectedListener.onc10Selected(item);
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement Onc10SelectedListener");
        }

    }

    @Override
    public void onConfirmation(String description) {
        mCreatePostPresenter.createPost(c10, description, photoFilePath);
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
