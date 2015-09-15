package com.disorder.looxlike.fragments;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.disorder.looxlike.R;
import com.disorder.presentation.view.HomeView;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment implements HomeView {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @BindString(R.string.app_name)
    String mToolbarTitle;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);
        mToolbar.setTitle(mToolbarTitle);
        mToolbar.inflateMenu(R.menu.menu_main);
        return root;
    }

    @Override
    public void showPage(@Page int page) {
        //TODO load right fragment
        throw new UnsupportedOperationException();
    }
}
