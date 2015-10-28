package com.disorder.networking.services.retrofit;

import com.disorder.networking.authorization.Authorization;
import com.disorder.networking.authorization.BasicAuthorization;
import com.disorder.networking.requests.CreatePostRequest;
import com.disorder.networking.responses.NewsPost;
import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.networking.utils.ApacheBase64Encoder;
import com.disorder.networking.utils.Base64Encoder;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotNull;

public class RetrofitLooxLikeAPITest {

    private static final String baseUrl = "http://54.93.89.176/LooxLikeAPI";
    private static final int pageToTest = 1;
    private static final LooxLikeAPI.LogLevel logLevel = LooxLikeAPI.LogLevel.FULL;

    private com.disorder.networking.services.retrofit.RetrofitLooxLikeAPI subjectUnderTest;

    @Test(expected = LooxLikeAPI.Unauthorized.class)
    public void shouldReturnNotAuthorized() throws Exception {
        subjectUnderTest = new RetrofitLooxLikeAPI(baseUrl, logLevel);
        subjectUnderTest.getNewsPosts(pageToTest).toBlocking().first();
    }

    @Test(expected = LooxLikeAPI.Unauthorized.class)
    public void shouldReturnBadRequest() throws Exception {
        Base64Encoder encoder = new ApacheBase64Encoder();
        Authorization authorization = new BasicAuthorization("username", "wrongPassword", encoder);
        subjectUnderTest = new RetrofitLooxLikeAPI(baseUrl, authorization, logLevel);
        subjectUnderTest.getNewsPosts(pageToTest).toBlocking().first();
    }

    private RetrofitLooxLikeAPI getAuthApi() {
        Base64Encoder encoder = new ApacheBase64Encoder();
        Authorization authorization = new BasicAuthorization("daniele", "password", encoder);
        return new RetrofitLooxLikeAPI(baseUrl, authorization, logLevel);
    }

    @Test
    public void testGetAllNews() throws Exception {
        subjectUnderTest = getAuthApi();
        NewsPost[] allPosts = subjectUnderTest.getNewsPosts(pageToTest).toBlocking().first();
        assertNotNull(allPosts);
    }

    private void testGetNewsByGender(LooxLikeAPI.Gender gender) {
        subjectUnderTest = getAuthApi();
        NewsPost[] allPosts = subjectUnderTest.getNewsPosts(gender, pageToTest).toBlocking().first();
        assertNotNull(allPosts);
    }

    @Test
    public void testGetMaleNews() throws Exception {
        testGetNewsByGender(LooxLikeAPI.Gender.MALE);
    }

    @Test
    public void testGetFemaleNews() throws Exception {
        testGetNewsByGender(LooxLikeAPI.Gender.FEMALE);
    }

    @Test
    public void testGeNoGenderNews() throws Exception {
        testGetNewsByGender(LooxLikeAPI.Gender.NOGENDER);
    }

    //TODO write real asserts when server is ready
    @Test(expected = LooxLikeAPI.NotFound.class)
    public void testCreatePost() throws Exception {
        subjectUnderTest = getAuthApi();
        File file = new File("cleanCode.png");
        CreatePostRequest request = new CreatePostRequest("description", "c10", file);
        NewsPost created = subjectUnderTest.createPost(request).toBlocking().first();
        assertNotNull(created);
    }
}