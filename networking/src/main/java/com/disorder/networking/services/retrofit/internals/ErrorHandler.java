package com.disorder.networking.services.retrofit.internals;

import com.disorder.networking.services.retrofit.RetrofitLooxLikeAPI;

import retrofit.RetrofitError;


public class ErrorHandler implements retrofit.ErrorHandler {


    @Override
    public Throwable handleError(RetrofitError cause) {
        if (cause.getKind() == RetrofitError.Kind.HTTP) {
            int status = cause.getResponse().getStatus();
            if (status == 401) {
                return new RetrofitLooxLikeAPI.Unauthorized(cause);
            } else if (status >= 500 && status <= 599) {
                return new RetrofitLooxLikeAPI.ServerError(cause);
            }
        }
        return cause;
    }
}
