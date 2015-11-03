package com.disorder.looxlike.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.disorder.looxlike.R;
import com.disorder.looxlike.application.LooxLikeApplication;
import com.disorder.looxlike.fragments.CreatePostFragment;
import com.disorder.looxlike.fragments.NewsTabsFragment;
import com.disorder.looxlike.fragments.ToolbarFragment;
import com.disorder.presentation.utils.IntentFactory;
import com.disorder.presentation.view.ToolbarView;

import java.io.IOException;

import javax.inject.Inject;

import icepick.State;

public class MainActivity extends BaseActivity implements NewsTabsFragment.OnCreatePostListener, CreatePostFragment.OnTakePictureRequestListener, CreatePostFragment.Onc10SelectedListener, CreatePostFragment.OnPostUploadedListener {

    private static final int REQUEST_TAKE_PHOTO = 1;
    private static final int containerId = R.id.fragment_container;

    @State
    String mCurrentPhotoPath;
    @State
    String mCurrent10;

    @Inject
    IntentFactory mIntentFactory;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LooxLikeApplication.getApplicationComponent(this).inject(this);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(containerId, ToolbarFragment.newInstance()).commit();
    }

    @Override
    public void onTakePictureRequest() {
        try {
            Intent takePicture = mIntentFactory.makeTakePicture(getPackageManager());
            mCurrentPhotoPath = takePicture.getStringExtra(MediaStore.EXTRA_OUTPUT);
            startActivityForResult(takePicture, REQUEST_TAKE_PHOTO);
        } catch (IOException ignored) {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            CreatePostFragment createPostFragment = CreatePostFragment.newInstance(mCurrent10, mCurrentPhotoPath);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, createPostFragment).commitAllowingStateLoss();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onCreatePost() {
        ToolbarFragment toolbarFragment = (ToolbarFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (toolbarFragment != null)
            toolbarFragment.showPage(ToolbarView.CREATE);
    }

    @Override
    public void onc10Selected(String c10) {
        mCurrent10 = c10;
    }

    @Override
    public void onPostUploaded() {
        getSupportFragmentManager().beginTransaction().replace(containerId, ToolbarFragment.newInstance()).commit();
    }

    @Override
    public void onBackPressed() {
        Fragment nestedFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (nestedFragment instanceof CreatePostFragment)
            getSupportFragmentManager().beginTransaction().replace(containerId, ToolbarFragment.newInstance()).commit();
        else if (nestedFragment != null) {
            FragmentManager childFragmentManager = nestedFragment.getChildFragmentManager();
            int backStackEntryCount = childFragmentManager.getBackStackEntryCount();
            if (backStackEntryCount > 0)
                childFragmentManager.popBackStack();
            else super.onBackPressed();
        } else super.onBackPressed();
    }
}
