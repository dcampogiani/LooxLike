package com.disorder.looxlike.fragments;

import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;

import com.disorder.looxlike.BuildConfig;
import com.disorder.looxlike.R;
import com.disorder.looxlike.activities.CreatePostActivity;

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
public class CheckOrderFragmentTest {

    private CheckOrderFragment subjectUnderTest;

    @Before
    public void setUp() throws Exception {
        subjectUnderTest = CheckOrderFragment.newInstance();
        SupportFragmentTestUtil.startVisibleFragment(subjectUnderTest, CreatePostActivity.class, R.id.fragment_container);
    }

    @Test
    public void testInsertOrderFieldIsShows() throws Exception {
        TextInputLayout textInputLayout = (TextInputLayout) subjectUnderTest.getView().findViewById(R.id.orderIdWrapper);
        EditText editText = (EditText) subjectUnderTest.getView().findViewById(R.id.orderId);
        assertThat(textInputLayout.getVisibility(), is(View.VISIBLE));
        assertThat(editText.getVisibility(), is(View.VISIBLE));

    }
}