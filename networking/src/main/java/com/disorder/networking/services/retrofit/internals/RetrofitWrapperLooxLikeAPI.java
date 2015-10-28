package com.disorder.networking.services.retrofit.internals;


import com.disorder.networking.responses.NewsPost;

import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.mime.TypedFile;
import rx.Observable;

public interface RetrofitWrapperLooxLikeAPI {

    @GET("/post/page/{pageNum}")
    Observable<NewsPost[]> getNewsPosts(@Query("gender") Character gender, @Path("pageNum") int page);

    @Multipart
    @POST("/post")
    Observable<NewsPost> createPost(@Part("description") String description, @Part("c10") String c10, @Part("photo") TypedFile file);
}
