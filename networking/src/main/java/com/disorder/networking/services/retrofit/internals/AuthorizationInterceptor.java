package com.disorder.networking.services.retrofit.internals;

import retrofit.RequestInterceptor;

public class AuthorizationInterceptor implements RequestInterceptor {

    private final String authorization;

    public AuthorizationInterceptor(String authorization) {
        this.authorization = authorization;
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader("Authorization", authorization);
    }
}
