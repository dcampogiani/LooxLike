package com.disorder.looxlike.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.disorder.looxlike.R;
import com.disorder.looxlike.fragments.CheckOrderFragment;

public class CreatePostActivity extends BaseActivity {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, CreatePostActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        int containerId = R.id.fragment_container;
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(containerId, CheckOrderFragment.newInstance()).commit();
    }
}
