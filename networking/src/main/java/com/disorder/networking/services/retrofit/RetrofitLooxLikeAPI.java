package com.disorder.networking.services.retrofit;


import com.disorder.networking.authorization.Authorization;
import com.disorder.networking.requests.CreatePostRequest;
import com.disorder.networking.responses.NewsPost;
import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.networking.services.retrofit.internals.AuthorizationInterceptor;
import com.disorder.networking.services.retrofit.internals.ErrorHandler;
import com.disorder.networking.services.retrofit.internals.OkHttpOrderDetailsDownloader;
import com.disorder.networking.services.retrofit.internals.RegExC10Extractor;
import com.disorder.networking.services.retrofit.internals.RetrofitWrapperLooxLikeAPI;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.mime.TypedFile;
import rx.Observable;
import rx.functions.Func0;
import rx.functions.Func1;

public class RetrofitLooxLikeAPI implements LooxLikeAPI {

    private static final String CONTENT_TYPE_IMAGE_PNG = "image/png";

    private final RetrofitWrapperLooxLikeAPI mRetrofitWrapperLooxLikeAPI;
    private final OkHttpOrderDetailsDownloader mOkHttpOrderDetailsDownloader;

    public static class Builder {
        private final String baseUrl;

        private LogLevel logLevel = LogLevel.NONE;
        private Authorization authorization = null;

        public Builder(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public Builder logLevel(LogLevel logLevel) {
            this.logLevel = logLevel;
            return this;
        }

        public Builder authorization(Authorization authorization) {
            this.authorization = authorization;
            return this;
        }

        public RetrofitLooxLikeAPI build() {
            return new RetrofitLooxLikeAPI(this);
        }

    }

    private RetrofitLooxLikeAPI(Builder builder) {
        OkHttpClient okHttpClient = new OkHttpClient();
        this.mOkHttpOrderDetailsDownloader = new OkHttpOrderDetailsDownloader(okHttpClient, new RegExC10Extractor());
        this.mRetrofitWrapperLooxLikeAPI = buildRetrofitWrapper(builder.baseUrl, builder.authorization, builder.logLevel, okHttpClient);
    }

    private RetrofitWrapperLooxLikeAPI buildRetrofitWrapper(String baseUrl, Authorization authorization, LogLevel logLevel, OkHttpClient httpClient) {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setLogLevel(getRetrofitLogLevel(logLevel))
                .setEndpoint(baseUrl)
                .setClient(new OkClient(httpClient))
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

    @Override
    public Observable<Boolean> orderHasItems(String orderId) {
        return getItemsOfOrder(orderId).map(new Func1<String[], Boolean>() {
            @Override
            public Boolean call(String[] strings) {
                return strings.length > 0;
            }
        });
    }

    @Override
    public Observable<String[]> getItemsOfOrder(final String orderId) {
        return Observable.defer(new Func0<Observable<String[]>>() {
            @Override
            public Observable<String[]> call() {
                try {
                    String[] result = mOkHttpOrderDetailsDownloader.getItemsC10(orderId);
                    return Observable.just(result);
                } catch (IOException e) {
                    Observable.error(e);
                }
                return Observable.just(new String[0]);
            }
        });
    }

    @Override
    public Observable<NewsPost[]> getLikedPosts(int page) {
        return mRetrofitWrapperLooxLikeAPI.getLikedPosts(page);
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
