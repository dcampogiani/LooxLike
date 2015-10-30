package com.disorder.looxlike.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;

import com.disorder.looxlike.R;
import com.disorder.looxlike.fragments.CreatePostFragment;
import com.disorder.looxlike.fragments.NewsTabsFragment;
import com.disorder.looxlike.fragments.ToolbarFragment;
import com.disorder.presentation.view.ToolbarView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends BaseActivity implements NewsTabsFragment.OnCreatePostListener, CreatePostFragment.OnTakePictureRequestListener, CreatePostFragment.Onc10SelectedListener {

    private static final int REQUEST_TAKE_PHOTO = 1;

    private static final String CURRENT_PHOTO_PATH_KEY = "CURRENT_PHOTO_PATH_KEY";
    private static final String CURRENT_C10_KEY = "CURRENT_C10_KEY";

    private static final int containerId = R.id.fragment_container;

    private String mCurrentPhotoPath;
    private String mCurrent10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(containerId, ToolbarFragment.newInstance()).commit();
        else {
            mCurrent10 = savedInstanceState.getString(CURRENT_C10_KEY);
            mCurrentPhotoPath = savedInstanceState.getString(CURRENT_PHOTO_PATH_KEY);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CURRENT_C10_KEY, mCurrent10);
        outState.putString(CURRENT_PHOTO_PATH_KEY, mCurrentPhotoPath);
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
        Fragment nestedFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (nestedFragment != null) {
            FragmentManager childFragmentManager = nestedFragment.getChildFragmentManager();
            int count = childFragmentManager.getBackStackEntryCount();
            if (count > 0)
                childFragmentManager.popBackStack();
            else super.onBackPressed();
        } else super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            CreatePostFragment createPostFragment = CreatePostFragment.newInstance(mCurrent10, mCurrentPhotoPath);
            //CreatePostConfirmationFragment createPostConfirmationFragment = CreatePostConfirmationFragment.newInstance(mCurrentPhotoPath);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, createPostFragment).commitAllowingStateLoss();
            //createPostFragment.getChildFragmentManager().beginTransaction().replace(CreatePostFragment.fragment_create_container, createPostConfirmationFragment).commitAllowingStateLoss();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onTakePictureRequest() {
        dispatchTakePictureIntent();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ignored) {

            }
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onc10Selected(String c10) {
        mCurrent10 = c10;
    }
}
