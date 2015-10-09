package com.disorder.looxlike.fragments;

import android.os.Build;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.view.View;

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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class NewsTabsFragmentTest {

    private NewsTabsFragment subjectUnderTest;

    @Before
    public void setUp() throws Exception {
        subjectUnderTest = NewsTabsFragment.newInstance();
        SupportFragmentTestUtil.startVisibleFragment(subjectUnderTest, MainActivity.class, R.id.fragment_container);
    }

    @Test
    public void tabsAreShown() {
        TabLayout tabs = getTabLayout();
        assertThat(tabs.getVisibility(), is(View.VISIBLE));
    }

    @Test
    public void tabsAreFour() {
        TabLayout tabs = getTabLayout();
        assertThat(tabs.getTabCount(), is(4));
    }

    @Test
    public void allTabIsShownAsFirst() {
        TabLayout tabs = getTabLayout();
        TabLayout.Tab allTab = tabs.getTabAt(0);
        String expectedText = getString(R.string.news_all);
        String tabText = allTab.getText().toString();
        assertThat(tabText, is(expectedText));
    }


    @Test
    public void womanTabIsShownAsSecond() {
        TabLayout tabs = getTabLayout();
        TabLayout.Tab womanTab = tabs.getTabAt(1);
        String expectedText = getString(R.string.news_female);
        String tabText = womanTab.getText().toString();
        assertThat(tabText, is(expectedText));
    }

    @Test
    public void manTabIsShownAsThird() {
        TabLayout tabs = getTabLayout();
        TabLayout.Tab manTab = tabs.getTabAt(2);
        String expectedText = getString(R.string.news_male);
        String tabText = manTab.getText().toString();
        assertThat(tabText, is(expectedText));
    }


    @Test
    public void noGenderTabIsShownAsFourth() {
        TabLayout tabs = getTabLayout();
        TabLayout.Tab noGenderTab = tabs.getTabAt(3);
        String expectedText = getString(R.string.news_no_gender);
        String tabText = noGenderTab.getText().toString();
        assertThat(tabText, is(expectedText));
    }

    private TabLayout getTabLayout() {
        return (TabLayout) subjectUnderTest.getView().findViewById(R.id.tabs);
    }

    private String getString(@StringRes int stringId) {
        return RuntimeEnvironment.application.getString(stringId);
    }


}