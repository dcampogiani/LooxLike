package com.disorder.looxlike.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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

public class MainActivity extends BaseActivity implements NewsTabsFragment.OnCreatePostListener, CreatePostFragment.OnTakePictureRequestListener {

    private static final int REQUEST_TAKE_PHOTO = 1;
    private String mCurrentPhotoPath;


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            ToolbarFragment toolbarFragment = (ToolbarFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (toolbarFragment != null) {
                CreatePostFragment createPostFragment = (CreatePostFragment) toolbarFragment.getChildFragmentManager().findFragmentById(ToolbarFragment.fragment_main_content);
                if (createPostFragment != null)
                    createPostFragment.setPhotoFilePath(mCurrentPhotoPath);

            }


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
            } catch (IOException ex) {

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
}
