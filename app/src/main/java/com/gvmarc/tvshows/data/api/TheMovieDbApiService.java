package com.gvmarc.tvshows.data.api;

import com.gvmarc.tvshows.BuildConfig;
import com.gvmarc.tvshows.data.entity.config.ConfigurationEntity;
import com.gvmarc.tvshows.data.entity.details.TvShowDetailsEntity;
import com.gvmarc.tvshows.data.entity.list.TvShowListEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TheMovieDbApiService {

    @GET("configuration?api_key=" + BuildConfig.API_KEY)
    Call<ConfigurationEntity> getConfiguration();

    @GET("tv/popular?api_key=" + BuildConfig.API_KEY)
    Call<TvShowListEntity> getPopularTvShows(@Query("page") int page);

    @GET("tv/{tvShowId}?api_key=" + BuildConfig.API_KEY)
    Call<TvShowDetailsEntity> getTvShowDetails(@Path("tvShowId") int tvShowId);
}
