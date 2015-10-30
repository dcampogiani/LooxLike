package com.disorder.looxlike.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;

import com.disorder.looxlike.R;
import com.disorder.looxlike.adapters.LikedPostAdapter;
import com.disorder.presentation.model.NewsPost;
import com.disorder.presentation.presenter.news.LikedPostPresenter;
import com.disorder.presentation.utils.Browser;
import com.disorder.presentation.utils.ImageDownloader;
import com.disorder.presentation.utils.ItemPageUrlEvaluator;
import com.disorder.presentation.view.NewsView;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindInt;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LikedPostFragment extends BaseFragment implements NewsView, LikedPostAdapter.PhotoListener, LikedPostAdapter.ScrollListener {


    public interface OnCreatePostListener {
        void onCreatePost();
    }

    @BindInt(R.integer.liked_posts_columns)
    int columns;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Inject
    ImageDownloader mImageDownloader;
    @Inject
    Browser mBrowser;
    @Inject
    LikedPostPresenter mLikedPostPresenter;
    @Inject
    ItemPageUrlEvaluator mItemPageUrlEvaluator;
    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;
    @Bind(R.id.fab)
    FloatingActionButton mFloatingActionButton;


    private LikedPostAdapter mLikedPostAdapter;
    private OnCreatePostListener mOnCreatePostListener;


    public static LikedPostFragment newInstance() {
        LikedPostFragment likedPostFragment = new LikedPostFragment();
        return likedPostFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mOnCreatePostListener = (OnCreatePostListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnCreatePostListener");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new StaggeredGridLayoutManager(columns, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_likedpost, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresentationComponent().inject(this);

        mLikedPostAdapter = new LikedPostAdapter(this, mImageDownloader, this);
        mRecyclerView.setAdapter(mLikedPostAdapter);
        mLikedPostPresenter.attachView(this);
        mLikedPostPresenter.loadMore();

    }

    @OnClick(R.id.fab)
    public void onCreatePostClick() {
        mOnCreatePostListener.onCreatePost();
    }

    @Override
    public void onDestroy() {
        if (mLikedPostPresenter != null)
            mLikedPostPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void updateModel(NewsPost[] model) {
        mLikedPostAdapter.addData(Arrays.asList(model));
    }

    @Override
    public void showLoading() {
        mProgressBar.animate().alpha(1).setInterpolator(new AccelerateDecelerateInterpolator());
    }

    @Override
    public void hideLoading() {
        mProgressBar.animate().alpha(0).setInterpolator(new AccelerateDecelerateInterpolator());
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void hideError() {

    }

    @Override
    public void onPhoto(String c10) {
        String url = mItemPageUrlEvaluator.evaluate(c10);
        mBrowser.navigateTo(url);
    }

    @Override
    public void almostAtTheEnd() {
        mLikedPostPresenter.loadMore();
    }
}
