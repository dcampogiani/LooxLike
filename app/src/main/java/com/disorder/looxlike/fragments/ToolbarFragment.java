package com.disorder.looxlike.fragments;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.disorder.looxlike.R;
import com.disorder.presentation.presenter.ToolbarPresenter;
import com.disorder.presentation.view.ToolbarView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

public class ToolbarFragment extends BaseFragment implements ToolbarView {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @BindString(R.string.app_name)
    String mToolbarTitle;

    @Inject
    ToolbarPresenter mToolbarPresenter;

    public static final int fragment_main_content = R.id.fragment_main_content;


    private MenuItemClickListener mMenuItemClickListener;

    public static ToolbarFragment newInstance() {
        return new ToolbarFragment();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getPresentationComponent().inject(this);
        mMenuItemClickListener = new MenuItemClickListener(mToolbarPresenter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_toolbar, container, false);
        ButterKnife.bind(this, root);
        mToolbar.setTitle(mToolbarTitle);
        mToolbar.inflateMenu(R.menu.menu_main);
        mToolbar.setOnMenuItemClickListener(mMenuItemClickListener);
        if (savedInstanceState == null)
            getChildFragmentManager().beginTransaction().replace(fragment_main_content, NewsTabsFragment.newInstance()).commit();
        return root;
    }

    @Override
    public void showPage(@Page int page) {
        //TODO load right fragment
        if (page == CREATE)
            getChildFragmentManager().beginTransaction().replace(fragment_main_content, CreatePostFragment.newInstance()).addToBackStack(null).commit();
        else
            throw new UnsupportedOperationException();
    }

    @Override
    public void onResume() {
        super.onResume();
        mToolbarPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        mToolbarPresenter.detachView();
        super.onDestroy();
    }


    private static class MenuItemClickListener implements Toolbar.OnMenuItemClickListener {

        private final ToolbarPresenter mToolbarPresenter;

        public MenuItemClickListener(ToolbarPresenter mToolbarPresenter) {
            this.mToolbarPresenter = mToolbarPresenter;
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            boolean handled = false;

            if (item.getItemId() == R.id.action_news) {
                handled = true;
                mToolbarPresenter.onNewsButtonClick();

            } else if (item.getItemId() == R.id.action_user) {
                handled = true;
                mToolbarPresenter.onUserButtonClick();

            } else if (item.getItemId() == R.id.action_favourites) {
                handled = true;
                mToolbarPresenter.onFavouritesButtonClick();
            }

            return handled;
        }
    }
}
