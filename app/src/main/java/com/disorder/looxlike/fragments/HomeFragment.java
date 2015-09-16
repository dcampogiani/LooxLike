package com.disorder.looxlike.fragments;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.disorder.looxlike.R;
import com.disorder.presentation.presenter.HomePresenter;
import com.disorder.presentation.view.HomeView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment implements HomeView {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @BindString(R.string.app_name)
    String mToolbarTitle;

    int fragment_main_content = R.id.fragment_main_content;

    @Inject
    HomePresenter mHomePresenter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public HomeFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getPresentationComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);
        mToolbar.setTitle(mToolbarTitle);
        mToolbar.inflateMenu(R.menu.menu_main);
        mToolbar.setOnMenuItemClickListener(new MenuItemClickListener());

        getChildFragmentManager().beginTransaction().replace(R.id.fragment_main_content, NewsFragment.newInstance()).commit();
        return root;
    }

    @Override
    public void showPage(@Page int page) {
        //TODO load right fragment
        throw new UnsupportedOperationException();
    }

    private class MenuItemClickListener implements Toolbar.OnMenuItemClickListener {

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            boolean handled = false;

            if (item.getItemId() == R.id.action_news) {
                handled = true;
                mHomePresenter.onNewsButtonClick();

            } else if (item.getItemId() == R.id.action_user) {
                handled = true;
                mHomePresenter.onUserButtonClick();

            } else if (item.getItemId() == R.id.action_favourites) {
                handled = true;
                mHomePresenter.onFavouritesButtonClick();
            }

            return handled;
        }
    }
}
