package com.pullrueepe.data.rest;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;


public class ApiInterceptor implements Interceptor {


    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_ACCEPT = "Accept";


    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request chainRequest = chain.request();
        Builder builder = chainRequest.newBuilder();
        builder.header(HEADER_CONTENT_TYPE, "application/json");
        builder.header(HEADER_ACCEPT, "application/json");
        Request request = builder.build();
        return chain.proceed(request);
    }
}
