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

    public static class Unauthorized extends RuntimeException {
        public Unauthorized(Throwable cause) {
            super(cause);
        }
    }

    public static class ServerError extends RuntimeException {
        public ServerError(Throwable cause) {
            super(cause);
        }
    }

    public static class NotFound extends RuntimeException {
        public NotFound(Throwable cause) {
            super(cause);
        }
    }

    private final RetrofitWrapperLooxLikeAPI mRetrofitWrapperLooxLikeAPI;

    public RetrofitLooxLikeAPI(String baseUrl) {
        this.mRetrofitWrapperLooxLikeAPI = buildRetrofitWrapper(baseUrl);
    }

    public RetrofitLooxLikeAPI(String baseUrl, Authorization authorization) {
        this.mRetrofitWrapperLooxLikeAPI = buildRetrofitWrapper(baseUrl, authorization);
    }

    private RetrofitWrapperLooxLikeAPI buildRetrofitWrapper(String baseUrl) {
        return buildRetrofitWrapper(baseUrl, null);
    }

    private RetrofitWrapperLooxLikeAPI buildRetrofitWrapper(String baseUrl, Authorization authorization) {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
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

}
