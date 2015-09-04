package com.disorder.looxlike.fragments;

import android.os.Build;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;

import com.disorder.looxlike.BuildConfig;
import com.disorder.looxlike.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class MainFragmentTest {

    MainFragment subjectUnderTest;

    @Before
    public void setUp() throws Exception {
        subjectUnderTest = MainFragment.newInstance();
        SupportFragmentTestUtil.startFragment(subjectUnderTest);
    }

    @Test
    public void AppNameIsShown() {
        String toolbarTitle = getToolbar().getTitle().toString();
        String applicationTitle = getString(R.string.app_name);
        assertThat(toolbarTitle, is(applicationTitle));

    }


    public Toolbar getToolbar() {
        return (Toolbar) subjectUnderTest.getView().findViewById(R.id.toolbar);
    }

    String getString(@StringRes int stringId) {
        return RuntimeEnvironment.application.getString(stringId);
    }
}