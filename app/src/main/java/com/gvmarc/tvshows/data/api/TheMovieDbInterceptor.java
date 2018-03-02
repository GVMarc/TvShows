package com.gvmarc.tvshows.data.api;


import com.gvmarc.tvshows.util.AppConstants;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TheMovieDbInterceptor implements Interceptor {

    private final static String PARAM_API_KEY = "api_key";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        HttpUrl originalUrl = original.url();

        HttpUrl url = originalUrl.newBuilder()
                .setQueryParameter(PARAM_API_KEY, AppConstants.API_KEY)
                .build();

        Request request = original.newBuilder().url(url).build();

        return chain.proceed(request);
    }

}
