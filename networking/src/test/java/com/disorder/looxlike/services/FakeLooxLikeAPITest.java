package com.disorder.looxlike.services;

import com.disorder.looxlike.responses.NewsPost;

import org.junit.Before;
import org.junit.Test;

import rx.observers.TestSubscriber;

public class FakeLooxLikeAPITest {

    FakeLooxLikeAPI subjectUnderTest;

    @Before
    public void setUp() throws Exception {
        subjectUnderTest = new FakeLooxLikeAPI();
    }

    @Test
    public void testGetNewsPostsMale() throws Exception {
        TestSubscriber<NewsPost[]> testSubscriber = new TestSubscriber<>();
        subjectUnderTest.getNewsPosts(LooxLikeAPI.Gender.MALE, 1).subscribe(testSubscriber);
        testSubscriber.assertValueCount(1);
    }

    @Test
    public void testGetNewsPostsFemale() throws Exception {
        TestSubscriber<NewsPost[]> testSubscriber = new TestSubscriber<>();
        subjectUnderTest.getNewsPosts(LooxLikeAPI.Gender.FEMALE, 1).subscribe(testSubscriber);
        testSubscriber.assertValueCount(1);
    }
}