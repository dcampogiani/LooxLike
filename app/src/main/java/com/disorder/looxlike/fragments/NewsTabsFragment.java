package com.disorder.looxlike.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.disorder.looxlike.R;
import com.disorder.looxlike.activities.CreatePostActivity;
import com.disorder.presentation.view.NewsView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsTabsFragment extends BaseFragment {

    @Bind(R.id.tabs)
    TabLayout mTabLayout;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    @Bind(R.id.fab)
    FloatingActionButton mFloatingActionButton;

    private PagerAdapter mPagerAdapter;

    public static NewsTabsFragment newInstance() {
        return new NewsTabsFragment();
    }

    public NewsTabsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPagerAdapter = new PagerAdapter(getChildFragmentManager(), getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news_tabs, container, false);
        ButterKnife.bind(this, root);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        return root;
    }

    @OnClick(R.id.fab)
    public void onCreatePostClick() {
        Context context = getContext();
        context.startActivity(CreatePostActivity.getCallingIntent(context));
    }

    private static class PagerAdapter extends FragmentStatePagerAdapter {

        private static final int pages = 4;

        private final Context mContext;

        public PagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.mContext = context;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return mContext.getString(R.string.news_all);
                case 1:
                    return mContext.getString(R.string.news_female);
                case 2:
                    return mContext.getString(R.string.news_male);
                case 3:
                    return mContext.getString(R.string.news_no_gender);
            }

            return Integer.toString(position);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return NewsFragment.newInstance();
                default:
                    return NewsFragment.newInstance(getGenderForPosition(position));
            }
        }

        @Override
        public int getCount() {
            return pages;
        }

        private
        @NewsView.Gender
        int getGenderForPosition(int position) {
            switch (position) {
                case 1:
                    return NewsView.FEMALE;
                case 2:
                    return NewsView.MALE;
                case 3:
                    return NewsView.NO_GENDER;
            }

            throw new IllegalArgumentException(Integer.toString(position));
        }
    }
}
