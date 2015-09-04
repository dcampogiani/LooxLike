package com.disorder.looxlike.fragments;

import android.support.v4.app.Fragment;

import com.disorder.looxlike.LooxLikeApplication;
import com.squareup.leakcanary.RefWatcher;


public abstract class BaseFragment extends Fragment {

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = LooxLikeApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
