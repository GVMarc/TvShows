package com.gvmarc.tvshows.domain.usecase;


import com.gvmarc.tvshows.data.api.TheMovieDbApiClient;
import com.gvmarc.tvshows.data.entity.list.TvShowEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetTvShowDetailsUseCase {

    private TheMovieDbApiClient apiClient;

    public GetTvShowDetailsUseCase() {
        apiClient = new TheMovieDbApiClient();
    }

    public void onDetailsRequested(int tvShowId, final Callback<TvShowEntity> callback) {
        apiClient.getTvShowDetails(tvShowId, new Callback<TvShowEntity>() {
            @Override
            public void onResponse(Call<TvShowEntity> call, Response<TvShowEntity> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<TvShowEntity> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }
}
