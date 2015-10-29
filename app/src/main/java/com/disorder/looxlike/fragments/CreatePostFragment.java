package com.disorder.looxlike.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.disorder.looxlike.R;

import butterknife.ButterKnife;

public class CreatePostFragment extends BaseFragment implements CheckFragmentOrder.OnOrderValidListener {

    private static final int fragment_create_container = R.id.fragment_create_container;


    public static CreatePostFragment newInstance() {
        return new CreatePostFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create, container, false);
        ButterKnife.bind(this, root);
        if (savedInstanceState == null)
            getChildFragmentManager().beginTransaction().replace(fragment_create_container, CheckFragmentOrder.newInstance()).commit();
        return root;
    }

    @Override
    public void onOrderValid(String orderId) {
        Toast.makeText(getContext(), orderId, Toast.LENGTH_LONG).show();
    }
}
