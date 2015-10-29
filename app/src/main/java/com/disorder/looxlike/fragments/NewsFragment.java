package com.disorder.looxlike.fragments;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;

import com.disorder.looxlike.R;
import com.disorder.looxlike.adapters.NewsPostAdapter;
import com.disorder.presentation.model.NewsPost;
import com.disorder.presentation.presenter.news.NewsPresenter;
import com.disorder.presentation.presenter.news.NewsPresenterFactory;
import com.disorder.presentation.utils.ImageDownloader;
import com.disorder.presentation.utils.UserAvatartUrlGenerator;
import com.disorder.presentation.view.NewsView;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindInt;
import butterknife.ButterKnife;

public class NewsFragment extends BaseFragment implements NewsView, NewsPostAdapter.PostListener, NewsPostAdapter.ScrollListener {

    private static final String GENDER_KEY = "GENDER_KEY";

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindInt(R.integer.news_posts_columns)
    int columns;
    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;

    @Inject
    NewsPresenterFactory mNewsPresenterFactory;
    @Inject
    ImageDownloader mImageDownloader;
    @Inject
    UserAvatartUrlGenerator mUserAvatartUrlGenerator;

    private CoordinatorLayout mCoordinatorLayout;

    private NewsPresenter mNewsPresenter;

    private NewsPostAdapter newsPostAdapter;
    private Snackbar currentSnackbar;

    public static NewsFragment newInstance() {
        NewsFragment newsFragment = new NewsFragment();
        Bundle args = new Bundle();
        newsFragment.setArguments(args);
        return newsFragment;
    }

    public static NewsFragment newInstance(@NewsView.Gender int gender) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putInt(GENDER_KEY, gender);
        newsFragment.setArguments(args);
        return newsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //TODO check
        //mRecyclerView.setHasFixedSize(true);
        final RecyclerView.LayoutManager mLayoutManager;

        if (columns == 1)
            mLayoutManager = new LinearLayoutManager(getActivity());
        else
            mLayoutManager = new StaggeredGridLayoutManager(columns, StaggeredGridLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mCoordinatorLayout = (CoordinatorLayout) getParentFragment().getView();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresentationComponent().inject(this);
        Bundle arguments = getArguments();
        @NewsView.Gender int gender = arguments.getInt(GENDER_KEY, -1);
        if (gender >= 0)
            mNewsPresenter = mNewsPresenterFactory.make(gender);
        else mNewsPresenter = mNewsPresenterFactory.make();

        newsPostAdapter = new NewsPostAdapter(this, mImageDownloader, this, mUserAvatartUrlGenerator);
        mRecyclerView.setAdapter(newsPostAdapter);
        mNewsPresenter.attachView(this);
        mNewsPresenter.loadMore();
    }

    @Override
    public void onDestroy() {
        if (mNewsPresenter != null)
            mNewsPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void updateModel(NewsPost[] model) {
        newsPostAdapter.addData(Arrays.asList(model));
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
        currentSnackbar = Snackbar.make(mCoordinatorLayout, errorMessage, Snackbar.LENGTH_INDEFINITE);
        currentSnackbar.show();
    }

    @Override
    public void hideError() {
        if (currentSnackbar != null && currentSnackbar.isShown())
            currentSnackbar.dismiss();
    }

    @Override
    public void onUser(NewsPost newsPost) {
        currentSnackbar = Snackbar.make(mCoordinatorLayout, getString(R.string.coming_soon) + " " + newsPost.username() + " profile", Snackbar.LENGTH_SHORT);
        currentSnackbar.show();
    }

    @Override
    public void onLike(NewsPost newsPost) {
        currentSnackbar = Snackbar.make(mCoordinatorLayout, getString(R.string.coming_soon) + " like post " + newsPost.id(), Snackbar.LENGTH_SHORT);
        currentSnackbar.show();
    }

    @Override
    public void onBuy(NewsPost newsPost) {
        mNewsPresenter.showItemInBrowser(newsPost.c10());
    }

    @Override
    public void almostAtTheEnd() {
        mNewsPresenter.loadMore();
    }
}
