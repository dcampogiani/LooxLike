package com.disorder.networking.services.retrofit;


import com.disorder.networking.authorization.Authorization;
import com.disorder.networking.requests.CreatePostRequest;
import com.disorder.networking.responses.NewsPost;
import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.networking.services.retrofit.internals.AuthorizationInterceptor;
import com.disorder.networking.services.retrofit.internals.ErrorHandler;
import com.disorder.networking.services.retrofit.internals.RetrofitWrapperLooxLikeAPI;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.mime.TypedFile;
import rx.Observable;

public class RetrofitLooxLikeAPI implements LooxLikeAPI {

    private static final String CONTENT_TYPE_IMAGE_PNG = "image/png";

    private final RetrofitWrapperLooxLikeAPI mRetrofitWrapperLooxLikeAPI;

    public RetrofitLooxLikeAPI(String baseUrl) {
        this.mRetrofitWrapperLooxLikeAPI = buildRetrofitWrapper(baseUrl);
    }

    public RetrofitLooxLikeAPI(String baseUrl, Authorization authorization) {
        this.mRetrofitWrapperLooxLikeAPI = buildRetrofitWrapper(baseUrl, authorization, LogLevel.NONE);
    }

    public RetrofitLooxLikeAPI(String baseUrl, LogLevel logLevel) {
        this.mRetrofitWrapperLooxLikeAPI = buildRetrofitWrapper(baseUrl, null, logLevel);
    }

    public RetrofitLooxLikeAPI(String baseUrl, Authorization authorization, LogLevel logLevel) {
        this.mRetrofitWrapperLooxLikeAPI = buildRetrofitWrapper(baseUrl, authorization, logLevel);
    }

    private RetrofitWrapperLooxLikeAPI buildRetrofitWrapper(String baseUrl) {
        return buildRetrofitWrapper(baseUrl, null, LogLevel.NONE);
    }

    private RetrofitWrapperLooxLikeAPI buildRetrofitWrapper(String baseUrl, Authorization authorization, LogLevel logLevel) {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setLogLevel(getRetrofitLogLevel(logLevel))
                .setEndpoint(baseUrl)
                .setClient(new OkClient())
                .setErrorHandler(new ErrorHandler());

        if (authorization != null) {
            RequestInterceptor authorizationInterceptor = new AuthorizationInterceptor(authorization.getHeader());
            builder.setRequestInterceptor(authorizationInterceptor);
        }
        RestAdapter restAdapter = builder.build();
        return restAdapter.create(RetrofitWrapperLooxLikeAPI.class);
    }

    @Override
    public Observable<NewsPost[]> getNewsPosts(int page) {
        return mRetrofitWrapperLooxLikeAPI.getNewsPosts(null, page);
    }

    @Override
    public Observable<NewsPost[]> getNewsPosts(Gender gender, int page) {
        return mRetrofitWrapperLooxLikeAPI.getNewsPosts(gender.queryParameter(), page);
    }

    @Override
    public Observable<NewsPost> createPost(CreatePostRequest request) {
        TypedFile typedFile = new TypedFile(CONTENT_TYPE_IMAGE_PNG, request.getFile());
        return mRetrofitWrapperLooxLikeAPI.createPost(request.getDescription(), request.getC10(), typedFile);
    }

    private RestAdapter.LogLevel getRetrofitLogLevel(LooxLikeAPI.LogLevel logLevel) {

        if (logLevel == LooxLikeAPI.LogLevel.FULL)
            return RestAdapter.LogLevel.FULL;
        else if (logLevel == LooxLikeAPI.LogLevel.BASIC)
            return RestAdapter.LogLevel.BASIC;
        else if (logLevel == LogLevel.HEADERS)
            return RestAdapter.LogLevel.HEADERS;
        else if (logLevel == LogLevel.HEADERS_AND_ARGS)
            return RestAdapter.LogLevel.HEADERS_AND_ARGS;
        return RestAdapter.LogLevel.NONE;
    }
}
