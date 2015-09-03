package com.disorder.looxlike;

import android.os.Build;
import android.support.annotation.IdRes;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTest {

    private MainActivity subjectUnderTest;

    @IdRes
    private int idMainFragment = R.id.fragment_main;

    @Before
    public void setUp() throws Exception {
        subjectUnderTest = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void mainFragmentIsShown() {
        MainActivityFragment mainActivityFragment = (MainActivityFragment) subjectUnderTest.getSupportFragmentManager().findFragmentById(idMainFragment);
        assertNotNull(mainActivityFragment);
    }
}