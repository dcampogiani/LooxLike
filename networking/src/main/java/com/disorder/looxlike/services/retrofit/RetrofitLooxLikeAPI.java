package com.disorder.looxlike.services.retrofit;


import com.disorder.looxlike.authorization.Authorization;
import com.disorder.looxlike.responses.NewsPost;
import com.disorder.looxlike.services.LooxLikeAPI;
import com.disorder.looxlike.services.retrofit.internals.AuthorizationInterceptor;
import com.disorder.looxlike.services.retrofit.internals.RetrofitWrapperLooxLikeAPI;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import rx.Observable;

public class RetrofitLooxLikeAPI implements LooxLikeAPI {

    private final String baseUrl;
    private final Authorization authorization;
    private final RetrofitWrapperLooxLikeAPI mRetrofitWrapperLooxLikeAPI;

    public RetrofitLooxLikeAPI(String baseUrl, Authorization authorization) {
        this.baseUrl = baseUrl;
        this.authorization = authorization;
        this.mRetrofitWrapperLooxLikeAPI = buildRetrofitWrapper();

    }

    private RetrofitWrapperLooxLikeAPI buildRetrofitWrapper() {
        RequestInterceptor authorizationInterceptor = new AuthorizationInterceptor(authorization.getHeader());
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setRequestInterceptor(authorizationInterceptor)
                .setEndpoint(baseUrl)
                .setClient(new OkClient())
                .build();
        return restAdapter.create(RetrofitWrapperLooxLikeAPI.class);
    }

    @Override
    public Observable<NewsPost[]> getNewsPosts(Gender gender, int page) {
        return mRetrofitWrapperLooxLikeAPI.getNewsPosts(gender.asText(), page);
    }

}
