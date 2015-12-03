package com.disorder.presentation.presenter.creation;


import com.disorder.presentation.presenter.Presenter;
import com.disorder.presentation.view.creation.CreatePostView;

public interface CreatePostPresenter extends Presenter<CreatePostView> {

    void createPost(String c10, String description, String photoFilePath);
}
