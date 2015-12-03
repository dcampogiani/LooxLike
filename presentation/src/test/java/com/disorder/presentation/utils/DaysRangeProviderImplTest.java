package com.disorder.presentation.utils;

import android.content.Context;
import android.os.Build;

import com.disorder.presentation.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class DaysRangeProviderImplTest {

    private DaysRangeProviderImpl subjectUnderTest;

    @Before
    public void setUp() throws Exception {
        Context context = RuntimeEnvironment.application;
        subjectUnderTest = new DaysRangeProviderImpl(context);
    }

    @Test
    public void shouldBeToday() {
        String expected = "Today";
        String result = subjectUnderTest.getString(0);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldBeYesterday() {
        String expected = "Yesterday";
        String result = subjectUnderTest.getString(1);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldBeSevenDaysAgo() {
        String expected = "7 days ago";
        String result = subjectUnderTest.getString(7);
        assertThat(result).isEqualTo(expected);
    }

}