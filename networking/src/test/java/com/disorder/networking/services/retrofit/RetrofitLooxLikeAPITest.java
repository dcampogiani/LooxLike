package com.disorder.networking.services.retrofit;

import com.disorder.networking.authorization.Authorization;
import com.disorder.networking.authorization.BasicAuthorization;
import com.disorder.networking.utils.ApacheBase64Encoder;
import com.disorder.networking.utils.Base64Encoder;
import com.disorder.networking.services.retrofit.RetrofitLooxLikeAPI;

import org.junit.Before;
import org.junit.Test;

public class RetrofitLooxLikeAPITest {

    private static final String baseUrl = "http://54.93.89.176/LooxLikeAPI/api";

    private com.disorder.networking.services.retrofit.RetrofitLooxLikeAPI subjectUnderTest;

    @Before
    public void setUp() throws Exception {
        //TODO setup baseUrl and authorization
        Base64Encoder encoder = new ApacheBase64Encoder();
        Authorization authorization = new BasicAuthorization("pippo", "pluto", encoder);
        subjectUnderTest = new RetrofitLooxLikeAPI(baseUrl, authorization);
    }

    @Test
    public void testGetNewsPosts() throws Exception {
        //TODO
        /*
        This test is actually done and could be run,
        but until the server will respond with data,
        it will fail.
        TestSubscriber<NewsPost[]> testSubscriber = new TestSubscriber<>();
        subjectUnderTest.getNewsPosts(LooxLikeAPI.Gender.MALE, 1).subscribe(testSubscriber);
        testSubscriber.assertValueCount(1);
        */
    }
}