package com.disorder.looxlike;

import android.app.Fragment;

import com.squareup.leakcanary.RefWatcher;


public abstract class BaseFragment extends Fragment {

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = LooxLikeApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
