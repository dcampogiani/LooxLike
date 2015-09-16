package com.disorder.looxlike.fragments;

import android.os.Build;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.disorder.looxlike.BuildConfig;
import com.disorder.looxlike.R;
import com.disorder.looxlike.activities.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class HomeFragmentTest {

    HomeFragment subjectUnderTest;

    @Before
    public void setUp() throws Exception {
        subjectUnderTest = HomeFragment.newInstance();
        SupportFragmentTestUtil.startVisibleFragment(subjectUnderTest, MainActivity.class, R.id.fragment_container);
    }

    @Test
    public void appNameIsShown() {
        String toolbarTitle = getToolbar().getTitle().toString();
        String applicationTitle = getString(R.string.app_name);
        assertThat(toolbarTitle, is(applicationTitle));

    }

    @Test
    public void newsIconIsShown() {
        MenuItem news = getNewsButton();
        assertTrue(news.isVisible());
    }

    @Test
    public void userIconIsShown() {
        MenuItem user = getUserButton();
        assertTrue(user.isVisible());
    }

    @Test
    public void favouritesIconIsShown() {
        MenuItem favourites = getFavouritesButton();
        assertTrue(favourites.isVisible());
    }


    public MenuItem getNewsButton() {
        return getToolbar().getMenu().findItem(R.id.action_news);
    }

    public MenuItem getUserButton() {
        return getToolbar().getMenu().findItem(R.id.action_user);
    }

    public MenuItem getFavouritesButton() {
        return getToolbar().getMenu().findItem(R.id.action_favourites);
    }

    public Toolbar getToolbar() {
        return (Toolbar) subjectUnderTest.getView().findViewById(R.id.toolbar);
    }

    String getString(@StringRes int stringId) {
        return RuntimeEnvironment.application.getString(stringId);
    }
}