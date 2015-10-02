package com.disorder.looxlike.activities;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.Menu;

import com.disorder.looxlike.R;
import com.disorder.looxlike.fragments.HomeFragment;

public class MainActivity extends BaseActivity {

    @IdRes
    private final int containerId = R.id.fragment_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(containerId, HomeFragment.newInstance()).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
