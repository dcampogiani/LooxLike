package com.disorder.looxlike.fragments;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;

import com.disorder.looxlike.R;
import com.disorder.looxlike.adapters.ItemAdapter;
import com.disorder.presentation.presenter.creation.ItemSelectionPresenter;
import com.disorder.presentation.utils.ImageDownloader;
import com.disorder.presentation.utils.ImageUrlGenerator;
import com.disorder.presentation.view.creation.ItemSelectionView;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ItemSelectionFragment extends BaseFragment implements ItemSelectionView, ItemAdapter.OnItemSelectedListener {


    public interface OnItemSelectedListener {
        void onItemSelected(String item);
    }

    private static final String ORDER_ID_KEY = "ORDER_ID_KEY";
    private static final int columns = 2;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;

    @Inject
    ItemSelectionPresenter mItemSelectionPresenter;
    @Inject
    ImageDownloader mImageDownloader;
    @Inject
    ImageUrlGenerator mImageUrlGenerator;

    private ItemAdapter mItemAdapter;

    public static ItemSelectionFragment newInstance(String orderId) {
        ItemSelectionFragment newsFragment = new ItemSelectionFragment();
        Bundle args = new Bundle();
        args.putString(ORDER_ID_KEY, orderId);
        newsFragment.setArguments(args);
        return newsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_item_selection, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mRecyclerView.setHasFixedSize(true);
        final RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new StaggeredGridLayoutManager(columns, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresentationComponent().inject(this);
        Bundle arguments = getArguments();

        String orderId = arguments.getString(ORDER_ID_KEY);

        mItemSelectionPresenter.attachView(this);
        mItemSelectionPresenter.loadItemsForOrder(orderId);

        mItemAdapter = new ItemAdapter(this, mImageDownloader, mImageUrlGenerator);
        mRecyclerView.setAdapter(mItemAdapter);

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
    public void setModel(String[] items) {
        mItemAdapter.setData(Arrays.asList(items));
    }

    @Override
    public void onItemSelected(String item) {

        OnItemSelectedListener onItemSelected;
        try {
            onItemSelected = (OnItemSelectedListener) getParentFragment();
            onItemSelected.onItemSelected(item);
        } catch (ClassCastException e) {
            throw new ClassCastException(getParentFragment().toString()
                    + " must implement OnItemSelectedListener");
        }

    }
}
