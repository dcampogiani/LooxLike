package com.disorder.looxlike.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.disorder.looxlike.R;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreatePostConfirmationFragment extends BaseFragment {

    private static final String PHOTO_FILE_PATH = "PHOTO_FILE_PATH";

    public interface OnConfirmationListener {
        void onConfirmation(String description);
    }

    @Bind(R.id.image)
    ImageView mImageView;
    @Bind(R.id.descriptionWrapper)
    TextInputLayout mTextInputLayout;
    @Bind(R.id.description)
    EditText mEditText;
    @Bind(R.id.uploadButton)
    Button mButton;

    public static CreatePostConfirmationFragment newInstance(String photoFilePath) {
        CreatePostConfirmationFragment newsFragment = new CreatePostConfirmationFragment();
        Bundle args = new Bundle();
        args.putString(PHOTO_FILE_PATH, photoFilePath);
        newsFragment.setArguments(args);
        return newsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create_post_confirmation, container, false);
        ButterKnife.bind(this, root);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String description = mEditText.getText().toString();
                if (description.length() > 0) {

                    OnConfirmationListener onConfirmationListener;
                    try {
                        onConfirmationListener = (OnConfirmationListener) getParentFragment();
                        onConfirmationListener.onConfirmation(description);
                    } catch (ClassCastException e) {
                        throw new ClassCastException(getParentFragment().toString()
                                + " must implement OnConfirmationListener");
                    }

                } else {
                    mTextInputLayout.setError(getString(R.string.description_must_be_valid));
                }
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        String imagePath = getArguments().getString(PHOTO_FILE_PATH);
        File imgFile = new File(imagePath);
        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            mImageView.setImageBitmap(myBitmap);
        }
    }
}
