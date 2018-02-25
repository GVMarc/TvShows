package com.gvmarc.tvshows.data.api;

import com.gvmarc.tvshows.BuildConfig;
import com.gvmarc.tvshows.data.entity.TvShowListEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.Query;

public interface TvShowsApiService {

    @GET("tv/popular?api_key=" + BuildConfig.API_KEY)
    Call<TvShowListEntity> tvPopular(@Query("page") int page);
}
