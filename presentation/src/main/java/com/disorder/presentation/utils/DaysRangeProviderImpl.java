package com.disorder.presentation.utils;

import android.content.Context;

import com.disorder.presentation.R;

import javax.inject.Inject;

public class DaysRangeProviderImpl implements DaysRangeProvider {

    private Context mContex;

    @Inject
    public DaysRangeProviderImpl(Context mContex) {
        this.mContex = mContex;
    }

    @Override
    public String getString(int days) {
        return mContex.getResources().getQuantityString(R.plurals.days, days, days);
    }
}
