package com.disorder.networking.services;

import com.disorder.networking.responses.NewsPost;

import org.junit.Before;
import org.junit.Test;

import rx.observers.TestSubscriber;

public class FakeLooxLikeAPITest {

    com.disorder.networking.services.FakeLooxLikeAPI subjectUnderTest;

    @Before
    public void setUp() throws Exception {
        subjectUnderTest = new com.disorder.networking.services.FakeLooxLikeAPI();
    }

    @Test
    public void testGetNewsPostsMale() throws Exception {
        TestSubscriber<NewsPost[]> testSubscriber = new TestSubscriber<>();
        subjectUnderTest.getNewsPosts(com.disorder.networking.services.LooxLikeAPI.Gender.MALE, 1).subscribe(testSubscriber);
        testSubscriber.assertValueCount(1);
    }

    @Test
    public void testGetNewsPostsFemale() throws Exception {
        TestSubscriber<NewsPost[]> testSubscriber = new TestSubscriber<>();
        subjectUnderTest.getNewsPosts(LooxLikeAPI.Gender.FEMALE, 1).subscribe(testSubscriber);
        testSubscriber.assertValueCount(1);
    }

    @Test
    public void testGetNewsPostsNoGender() throws Exception {
        TestSubscriber<NewsPost[]> testSubscriber = new TestSubscriber<>();
        subjectUnderTest.getNewsPosts(LooxLikeAPI.Gender.NOGENDER, 1).subscribe(testSubscriber);
        testSubscriber.assertValueCount(1);
    }

    @Test
    public void testGetAllNewsPosts() throws Exception {
        TestSubscriber<NewsPost[]> testSubscriber = new TestSubscriber<>();
        subjectUnderTest.getNewsPosts(1).subscribe(testSubscriber);
        testSubscriber.assertValueCount(1);
    }
}