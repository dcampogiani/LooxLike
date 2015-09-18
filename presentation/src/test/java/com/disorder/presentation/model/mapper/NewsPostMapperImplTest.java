package com.disorder.presentation.model.mapper;

import com.disorder.networking.responses.NewsPost;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class NewsPostMapperImplTest {

    NewsPostMapperImpl subjectUnderTest;

    @Before
    public void setUp() throws Exception {
        subjectUnderTest = new NewsPostMapperImpl();
    }

    @Test
    public void testSingleMap() throws Exception {
        NewsPost networkResponse = new NewsPost(1, "photo", "description", "itemID", 2, "rlovino", true);
        com.disorder.presentation.model.NewsPost expected = com.disorder.presentation.model.NewsPost.create(1, "photo", "description", "itemID", 2, "rlovino", true);
        com.disorder.presentation.model.NewsPost result = subjectUnderTest.map(networkResponse);
        assertThat(result.id(), is(expected.id()));
        assertThat(result.photoUrl(), is(expected.photoUrl()));
        assertThat(result.description(), is(expected.description()));
        assertThat(result.itemId(), is(expected.itemId()));
        assertThat(result.likes(), is(expected.likes()));
        assertThat(result.username(), is(expected.username()));
        assertThat(result.liked(), is(expected.liked()));

    }

    @Test
    public void testArrayMap() throws Exception {
        NewsPost[] networkResponse = new NewsPost[2];
        networkResponse[0] = new NewsPost(1, "photo", "description", "itemID", 2, "rlovino", true);
        networkResponse[1] = new NewsPost(2, "phot2", "descriptio2", "itemID2", 3, "rlovino", false);
        int expectedLenght = 2;
        com.disorder.presentation.model.NewsPost[] result = subjectUnderTest.map(networkResponse);
        assertThat(result.length, is(expectedLenght));
    }
}