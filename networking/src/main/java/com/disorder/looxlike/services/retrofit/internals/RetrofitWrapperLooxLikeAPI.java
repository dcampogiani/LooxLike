package com.disorder.looxlike.services.retrofit.internals;


import com.disorder.looxlike.responses.NewsPost;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface RetrofitWrapperLooxLikeAPI {

    @GET("/post/page/{pageNum}")
    Observable<NewsPost[]> getNewsPosts(@Query("gender") String gender, @Path("pageNum") int page);
}
