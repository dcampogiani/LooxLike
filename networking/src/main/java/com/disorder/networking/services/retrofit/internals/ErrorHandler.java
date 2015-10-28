package com.disorder.networking.services.retrofit.internals;

import com.disorder.networking.services.LooxLikeAPI;

import retrofit.RetrofitError;


public class ErrorHandler implements retrofit.ErrorHandler {


    @Override
    public Throwable handleError(RetrofitError cause) {
        if (cause.getKind() == RetrofitError.Kind.HTTP) {
            int status = cause.getResponse().getStatus();
            if (status == 401)
                return new LooxLikeAPI.Unauthorized(cause);
            else if (status == 404)
                return new LooxLikeAPI.NotFound(cause);
            else if (status >= 500 && status <= 599)
                return new LooxLikeAPI.ServerError(cause);
        }
        return cause;
    }
}
