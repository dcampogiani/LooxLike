package com.disorder.networking.services.retrofit;

import com.disorder.networking.authorization.Authorization;
import com.disorder.networking.authorization.BasicAuthorization;
import com.disorder.networking.requests.CreatePostRequest;
import com.disorder.networking.responses.NewsPost;
import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.networking.utils.ApacheBase64Encoder;
import com.disorder.networking.utils.Base64Encoder;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
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


    @Test
    @Ignore
    public void testCreatePost() throws Exception {
        subjectUnderTest = getAuthApi();
        String description = "description";
        String c10 = "c10";
        File file = new File("cleanCode.png");
        CreatePostRequest request = new CreatePostRequest(description, c10, file);
        NewsPost created = subjectUnderTest.createPost(request).toBlocking().first();
        assertThat(created.getDescription(), is(description));
        assertThat(created.getC10(), is(c10));
        assertThat(created.getNumLikes(), is(0));
        assertThat(created.isLiked(), is(false));
    }

    @Test
    @Ignore
    public void testGetItemsFromNotExistingOrder() throws Exception {
        subjectUnderTest = getAuthApi();
        String[] items = subjectUnderTest.getItemsOfOrder("pippo").toBlocking().first();
        assertThat(items.length, is(0));
    }

    @Test
    @Ignore
    public void testGetOneItemFromOrder() throws Exception {
        subjectUnderTest = getAuthApi();
        String[] items = subjectUnderTest.getItemsOfOrder("2810Y2C321502A").toBlocking().first();
        String result = items[0];
        assertThat(items.length, is(1));
        assertThat(result, is("36731894KJ"));
    }

    @Test
    @Ignore
    public void testGetTwoItemsFromOrder() throws Exception {
        subjectUnderTest = getAuthApi();
        String[] items = subjectUnderTest.getItemsOfOrder("2310Y5AC115029").toBlocking().first();
        String firstResult = items[0];
        String secondResult = items[1];
        assertThat(items.length, is(2));
        assertThat(firstResult, is("37647512DD"));
        assertThat(secondResult, is("37647512TK"));
    }

    @Test
    @Ignore
    public void testOrderHasItems() throws Exception {
        subjectUnderTest = getAuthApi();
        boolean result = subjectUnderTest.orderHasItems("2310Y5AC115029").toBlocking().first();
        assertThat(result, is(true));
    }

    @Test
    @Ignore
    public void testOrderDoesNotHaveItems() throws Exception {
        subjectUnderTest = getAuthApi();
        boolean result = subjectUnderTest.orderHasItems("pippo").toBlocking().first();
        assertThat(result, is(false));
    }

    @Test
    @Ignore
    public void testGetLikedPost() throws Exception {
        subjectUnderTest = getAuthApi();
        NewsPost[] result = subjectUnderTest.getLikedPosts(pageToTest).toBlocking().single();
        assertNotNull(result);
    }
}