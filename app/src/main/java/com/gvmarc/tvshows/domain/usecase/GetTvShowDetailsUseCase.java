package com.gvmarc.tvshows.domain.usecase;


import com.gvmarc.tvshows.data.api.TheMovieDbApiClient;
import com.gvmarc.tvshows.data.entity.list.TvShowEntity;

import retrofit2.Callback;

public class GetTvShowDetailsUseCase {

    private TheMovieDbApiClient apiClient;

    public GetTvShowDetailsUseCase() {
        apiClient = new TheMovieDbApiClient();
    }

    public void onDetailsRequested(int tvShowId, Callback<TvShowEntity> callback) {
        apiClient.getTvShowDetails(tvShowId, callback);
    }
}
