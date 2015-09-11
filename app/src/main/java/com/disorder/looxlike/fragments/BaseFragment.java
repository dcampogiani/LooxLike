package com.disorder.looxlike.fragments;

import android.support.v4.app.Fragment;

import com.disorder.looxlike.application.LeakDetector;
import com.disorder.looxlike.application.LooxLikeApplication;


public abstract class BaseFragment extends Fragment {

    @Override
    public void onDestroy() {
        super.onDestroy();
        LeakDetector leakDetector = LooxLikeApplication.getLeakDetector(getActivity());
        leakDetector.watch(this);
    }
}
