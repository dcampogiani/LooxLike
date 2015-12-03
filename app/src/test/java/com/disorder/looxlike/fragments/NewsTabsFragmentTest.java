package com.disorder.looxlike.fragments;

import android.os.Build;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
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
        String expectedText = getString(R.string.news_all);
        testTabText(0, expectedText);
    }

    private void testTabText(int index, String expected) {
        TabLayout tabs = getTabLayout();
        TabLayout.Tab tabUnderTest = tabs.getTabAt(index);
        String actual = getTabText(tabUnderTest);
        assertThat(actual, is(expected));
    }

    private String getTabText(TabLayout.Tab tab) {
        //noinspection ConstantConditions
        return tab.getText().toString();
    }


    @Test
    public void womanTabIsShownAsSecond() {
        String expectedText = getString(R.string.news_female);
        testTabText(1, expectedText);
    }

    @Test
    public void manTabIsShownAsThird() {
        String expectedText = getString(R.string.news_male);
        testTabText(2, expectedText);
    }


    @Test
    public void noGenderTabIsShownAsFourth() {
        String expectedText = getString(R.string.news_no_gender);
        testTabText(3, expectedText);
    }

    @Test
    public void FABIsShown() {
        @SuppressWarnings("ConstantConditions") FloatingActionButton fab = (FloatingActionButton) subjectUnderTest.getView().findViewById(R.id.fab);
        assertThat(fab.getVisibility(), is(View.VISIBLE));
    }

    private TabLayout getTabLayout() {
        //noinspection ConstantConditions
        return (TabLayout) subjectUnderTest.getView().findViewById(R.id.tabs);
    }

    private String getString(@StringRes int stringId) {
        return RuntimeEnvironment.application.getString(stringId);
    }


}