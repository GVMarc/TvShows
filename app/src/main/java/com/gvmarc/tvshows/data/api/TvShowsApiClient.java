package com.gvmarc.tvshows.data.api;


import com.gvmarc.tvshows.BuildConfig;
import com.gvmarc.tvshows.data.entity.TvShowListEntity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvShowsApiClient {

    private final TvShowsApiService apiService;

    public TvShowsApiClient() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiService = retrofit.create(TvShowsApiService.class);
    }

    public void getPopularTvShows(int page, Callback<TvShowListEntity> callback) {
        apiService.tvPopular(page).enqueue(callback);
    }
}
