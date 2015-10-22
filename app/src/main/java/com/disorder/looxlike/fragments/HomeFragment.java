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

    @Inject
    HomePresenter mHomePresenter;

    private MenuItemClickListener mMenuItemClickListener;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getPresentationComponent().inject(this);
        mMenuItemClickListener = new MenuItemClickListener(mHomePresenter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);
        mToolbar.setTitle(mToolbarTitle);
        mToolbar.inflateMenu(R.menu.menu_main);
        mToolbar.setOnMenuItemClickListener(mMenuItemClickListener);
        int fragment_main_content = R.id.fragment_main_content;
        if (savedInstanceState == null)
            getChildFragmentManager().beginTransaction().replace(fragment_main_content, NewsTabsFragment.newInstance()).commit();
        return root;
    }

    @Override
    public void showPage(@Page int page) {
        //TODO load right fragment
        throw new UnsupportedOperationException();
    }

    private static class MenuItemClickListener implements Toolbar.OnMenuItemClickListener {

        private final HomePresenter mHomePresenter;

        public MenuItemClickListener(HomePresenter mHomePresenter) {
            this.mHomePresenter = mHomePresenter;
        }

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
