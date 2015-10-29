package com.disorder.looxlike.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;

import com.disorder.looxlike.R;
import com.disorder.looxlike.fragments.NewsTabsFragment;
import com.disorder.looxlike.fragments.ToolbarFragment;
import com.disorder.presentation.view.ToolbarView;

public class MainActivity extends BaseActivity implements NewsTabsFragment.OnCreatePostListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int containerId = R.id.fragment_container;
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(containerId, ToolbarFragment.newInstance()).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onCreatePost() {
        ToolbarFragment toolbarFragment = (ToolbarFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (toolbarFragment != null)
            toolbarFragment.showPage(ToolbarView.CREATE);
    }

    @Override
    public void onBackPressed() {
        ToolbarFragment toolbarFragment = (ToolbarFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (toolbarFragment != null) {
            FragmentManager childFragmentManager = toolbarFragment.getChildFragmentManager();
            int count = childFragmentManager.getBackStackEntryCount();
            if (count > 0)
                childFragmentManager.popBackStack();
            else super.onBackPressed();
        } else super.onBackPressed();
    }
}
