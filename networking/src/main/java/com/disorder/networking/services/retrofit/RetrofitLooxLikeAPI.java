package com.disorder.networking.services.retrofit;


import com.disorder.networking.authorization.Authorization;
import com.disorder.networking.responses.NewsPost;
import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.networking.services.retrofit.internals.AuthorizationInterceptor;
import com.disorder.networking.services.retrofit.internals.ErrorHandler;
import com.disorder.networking.services.retrofit.internals.RetrofitWrapperLooxLikeAPI;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import rx.Observable;

public class RetrofitLooxLikeAPI implements LooxLikeAPI {

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
        return mRetrofitWrapperLooxLikeAPI.getNewsPosts(gender.asText(), page);
    }

}
