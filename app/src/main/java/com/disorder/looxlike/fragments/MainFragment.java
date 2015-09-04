package com.disorder.looxlike.fragments;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.disorder.looxlike.R;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.BindString;
import butterknife.ButterKnife;

public class MainFragment extends BaseFragment {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @BindString(R.string.app_name)
    String mToolbarTitle;
    @BindColor(R.color.icons)
    int iconsColor;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);
        mToolbar.setTitleTextColor(iconsColor);
        mToolbar.setTitle(mToolbarTitle);
        return root;
    }
}
