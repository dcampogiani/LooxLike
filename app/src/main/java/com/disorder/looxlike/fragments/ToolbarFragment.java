package com.disorder.looxlike.fragments;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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

    private static final int fragment_main_content = R.id.fragment_main_content;

    @Bind(fragment_main_content)
    CoordinatorLayout mCoordinatorLayout;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @BindString(R.string.app_name)
    String mToolbarTitle;

    @Inject
    ToolbarPresenter mToolbarPresenter;

    public static ToolbarFragment newInstance() {
        return new ToolbarFragment();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getPresentationComponent().inject(this);
        mToolbar.setOnMenuItemClickListener(new MenuItemClickListener(mToolbarPresenter));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_toolbar, container, false);
        ButterKnife.bind(this, root);
        mToolbar.inflateMenu(R.menu.menu_main);
        if (savedInstanceState == null)
            loadFirstPage();
        return root;
    }

    private void loadFirstPage() {
        @Page int firstPage = NEWS;
        String firstFragmentTag = Integer.toString(firstPage);
        Fragment firstFragment = getFragmentForPage(firstPage);
        getChildFragmentManager().beginTransaction().add(fragment_main_content, firstFragment, firstFragmentTag).commit();
    }

    @Override
    public void showPage(@Page int page) {

        if (isPageVisible(page))
            return;

        if (page == USER) {
            Snackbar.make(mCoordinatorLayout, R.string.coming_soon, Snackbar.LENGTH_SHORT).show();
            return;
        }

        String fragmentTag = Integer.toString(page);
        Fragment fragmentToAdd = getFragmentForPage(page);
        getChildFragmentManager().beginTransaction().replace(fragment_main_content, fragmentToAdd, fragmentTag).addToBackStack(null).commit();
    }

    private boolean isPageVisible(@Page int page) {
        Fragment fragment = getChildFragmentManager().findFragmentByTag(Integer.toString(page));
        return (fragment != null && fragment.isVisible());
    }

    private Fragment getFragmentForPage(@Page int page) {
        switch (page) {
            case NEWS:
                return NewsTabsFragment.newInstance();
            case FAVOURITES:
                return LikedPostFragment.newInstance();
            case CREATE:
                return CreatePostFragment.newInstance();
            default:
                throw new IllegalArgumentException(Integer.toString(page));
        }
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
