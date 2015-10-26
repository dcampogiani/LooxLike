package com.disorder.networking.services.retrofit.internals;


import com.disorder.networking.responses.NewsPost;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface RetrofitWrapperLooxLikeAPI {

    @GET("/post/page/{pageNum}")
    Observable<NewsPost[]> getNewsPosts(@Query("gender") Character gender, @Path("pageNum") int page);
}
