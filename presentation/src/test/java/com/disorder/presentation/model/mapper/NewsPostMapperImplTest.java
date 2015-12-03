package com.disorder.presentation.model.mapper;

import com.disorder.networking.responses.NewsPost;
import com.disorder.presentation.utils.DateIntervalCalculator;
import com.disorder.presentation.utils.DaysRangeProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;


public class NewsPostMapperImplTest {

    private NewsPostMapperImpl subjectUnderTest;

    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    private DaysRangeProvider daysRangeProvider;
    @Mock
    private DateIntervalCalculator dateIntervalCalculator;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subjectUnderTest = new NewsPostMapperImpl(dateIntervalCalculator, daysRangeProvider);
    }

    @Test
    public void testSingleMap() throws Exception {
        NewsPost networkResponse = new NewsPost(1, "description", "photo", "itemID", "2015-10-11T20:44:55", "rlovino", 2, true);
        com.disorder.presentation.model.NewsPost expected = com.disorder.presentation.model.NewsPost.create(1, "description", "photo", "itemID", "howShouldITestIt", "rlovino", 2, true);
        com.disorder.presentation.model.NewsPost result = subjectUnderTest.map(networkResponse);
        assertThat(result.id()).isEqualTo(expected.id());
        assertThat(result.photoUrl()).isEqualTo(expected.photoUrl());
        assertThat(result.description()).isEqualTo(expected.description());
        assertThat(result.c10()).isEqualTo(expected.c10());
        assertThat(result.likes()).isEqualTo(expected.likes());
        assertThat(result.username()).isEqualTo(expected.username());
        assertThat(result.liked()).isEqualTo(expected.liked());
    }

    @Test
    public void testArrayMap() throws Exception {
        NewsPost[] networkResponse = new NewsPost[2];
        networkResponse[0] = new NewsPost(1, "description", "photo", "itemID", "2015-10-11T20:44:55", "rlovino", 2, true);
        networkResponse[1] = new NewsPost(2, "descriptio2", "phot2", "itemID2", "2015-10-11T20:44:55", "rlovino", 3, false);
        int expectedLength = 2;
        com.disorder.presentation.model.NewsPost[] result = subjectUnderTest.map(networkResponse);
        assertThat(result).hasSize(expectedLength);
    }
}