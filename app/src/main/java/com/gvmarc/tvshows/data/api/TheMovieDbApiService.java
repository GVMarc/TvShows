package com.gvmarc.tvshows.data.api;

import com.gvmarc.tvshows.data.entity.config.ConfigurationEntity;
import com.gvmarc.tvshows.data.entity.list.TvShowEntity;
import com.gvmarc.tvshows.data.entity.list.TvShowListEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TheMovieDbApiService {

    @GET("configuration")
    Call<ConfigurationEntity> getConfiguration();

    @GET("tv/popular")
    Call<TvShowListEntity> getPopularTvShows(@Query("page") int page);

    @GET("tv/{tvShowId}")
    Call<TvShowEntity> getTvShowDetails(@Path("tvShowId") int tvShowId);

    @GET("tv/{tvShowId}/similar")
    Call<TvShowListEntity> getSimilarTvShows(@Path("tvShowId") int tvShowId);
}
