package com.disorder.looxlike.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.disorder.looxlike.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsFragment extends BaseFragment {

    @Bind(R.id.tabs)
    TabLayout mTabLayout;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    public NewsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, root);

        mViewPager.setAdapter(new PagerAdapter(getChildFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
        return root;
    }

    private static class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Integer.toString(position);
        }

        @Override
        public Fragment getItem(int position) {
            return new DummyFragment();
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
