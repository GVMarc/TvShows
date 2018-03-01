package com.gvmarc.tvshows.data.api;


import com.gvmarc.tvshows.data.entity.config.ConfigurationEntity;
import com.gvmarc.tvshows.data.entity.list.TvShowEntity;
import com.gvmarc.tvshows.data.entity.list.TvShowListEntity;
import com.gvmarc.tvshows.util.AppConstants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TheMovieDbApiClient {

    private final TheMovieDbApiService apiService;

    public TheMovieDbApiClient() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiService = retrofit.create(TheMovieDbApiService.class);
    }

    public void getConfiguration(Callback<ConfigurationEntity> callback) {
        apiService.getConfiguration().enqueue(callback);
    }

    public void getPopularTvShows(int page, Callback<TvShowListEntity> callback) {
        apiService.getPopularTvShows(page).enqueue(callback);
    }

    public void getTvShowDetails(int tvShowId, Callback<TvShowEntity> callback) {
        apiService.getTvShowDetails(tvShowId).enqueue(callback);
    }
}
