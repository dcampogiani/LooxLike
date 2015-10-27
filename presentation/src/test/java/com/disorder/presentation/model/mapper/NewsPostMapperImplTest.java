package com.disorder.presentation.model.mapper;

import com.disorder.networking.responses.NewsPost;
import com.disorder.presentation.utils.DateIntervalCalculator;
import com.disorder.presentation.utils.DaysRangeProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NewsPostMapperImplTest {

    private NewsPostMapperImpl subjectUnderTest;
    private DaysRangeProvider daysRangeProvider;

    @Before
    public void setUp() throws Exception {
        DateIntervalCalculator dateIntervalCalculator = Mockito.mock(DateIntervalCalculator.class);
        daysRangeProvider = new DaysRangeProvider() {
            @Override
            public String getString(int days) {
                return "howShouldITestIt";
            }
        };
        subjectUnderTest = new NewsPostMapperImpl(dateIntervalCalculator, daysRangeProvider);
    }

    @Test
    public void testSingleMap() throws Exception {
        NewsPost networkResponse = new NewsPost(1, "description", "photo", "itemID", "2015-10-11T20:44:55", "rlovino", 2, true);
        com.disorder.presentation.model.NewsPost expected = com.disorder.presentation.model.NewsPost.create(1, "description", "photo", "itemID", "howShouldITestIt", "rlovino", 2, true);
        com.disorder.presentation.model.NewsPost result = subjectUnderTest.map(networkResponse);
        assertThat(result.id(), is(expected.id()));
        assertThat(result.photoUrl(), is(expected.photoUrl()));
        assertThat(result.description(), is(expected.description()));
        assertThat(result.c10(), is(expected.c10()));
        assertThat(result.likes(), is(expected.likes()));
        assertThat(result.username(), is(expected.username()));
        assertThat(result.liked(), is(expected.liked()));

    }

    @Test
    public void testArrayMap() throws Exception {
        NewsPost[] networkResponse = new NewsPost[2];
        networkResponse[0] = new NewsPost(1, "description", "photo", "itemID", "2015-10-11T20:44:55", "rlovino", 2, true);
        networkResponse[1] = new NewsPost(2, "descriptio2", "phot2", "itemID2", "2015-10-11T20:44:55", "rlovino", 3, false);
        int expectedLenght = 2;
        com.disorder.presentation.model.NewsPost[] result = subjectUnderTest.map(networkResponse);
        assertThat(result.length, is(expectedLenght));
    }
}