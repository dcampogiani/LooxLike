package com.disorder.networking.services.retrofit.internals;


import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class OkHttpOrderDetailsDownloader {

    private static final String baseUrl = "http://www.yoox.com/help/orders?OrderNumber=";

    private final OkHttpClient okHttpClient;
    private final C10Extractor mC10Extractor;


    public OkHttpOrderDetailsDownloader(OkHttpClient okHttpClient, C10Extractor c10Extractor) {
        this.okHttpClient = okHttpClient;
        this.mC10Extractor = c10Extractor;
    }

    public String[] getItemsC10(String orderId) throws IOException {

        Request request = new Request.Builder().url(baseUrl + orderId).build();
        Response response = okHttpClient.newCall(request).execute();
        String rawResponse = response.body().string();
        return mC10Extractor.extract(rawResponse);
    }
}
