package com.gvmarc.tvshows.domain.usecase;

import com.gvmarc.tvshows.data.api.TheMovieDbApiClient;
import com.gvmarc.tvshows.data.entity.list.TvShowListEntity;

import retrofit2.Callback;

public class GetTvShowsUseCase {

    private TheMovieDbApiClient apiClient;

    public GetTvShowsUseCase() {
        apiClient = new TheMovieDbApiClient();
    }

    public void onPopularTvShowsRequested(int page, Callback<TvShowListEntity> callback) {
        apiClient.getPopularTvShows(page, callback);
    }

    public void onSimilarTvShowsRequested(int tvShowId, Callback<TvShowListEntity> callback) {
        apiClient.getSimilarTvShows(tvShowId, callback);
    }
}
