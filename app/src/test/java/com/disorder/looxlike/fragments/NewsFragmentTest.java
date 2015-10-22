package com.disorder.looxlike.fragments;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.disorder.looxlike.BuildConfig;
import com.disorder.looxlike.R;
import com.disorder.looxlike.activities.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class NewsFragmentTest {

    private NewsFragment subjectUnderTest;

    @Before
    public void setUp() throws Exception {
        subjectUnderTest = NewsFragment.newInstance();
        SupportFragmentTestUtil.startVisibleFragment(subjectUnderTest, MainActivity.class, R.id.fragment_container);
    }

    @Test
    public void recyclerViewIsShown() {
        @SuppressWarnings("ConstantConditions") RecyclerView recyclerView = (RecyclerView) subjectUnderTest.getView().findViewById(R.id.recycler_view);
        assertThat(recyclerView.getVisibility(), is(View.VISIBLE));
    }
}