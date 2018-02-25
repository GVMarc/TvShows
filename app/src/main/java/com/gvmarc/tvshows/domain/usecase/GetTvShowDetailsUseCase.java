package com.gvmarc.tvshows.domain.usecase;


import com.gvmarc.tvshows.data.api.TheMovieDbApiClient;
import com.gvmarc.tvshows.data.entity.details.TvShowDetailsEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetTvShowDetailsUseCase {

    private TheMovieDbApiClient apiClient;
    private Callback callback;

    public GetTvShowDetailsUseCase(Callback callback) {
        this.callback = callback;
        apiClient = new TheMovieDbApiClient();
    }

    public void onDetailsRequested(int tvShowId) {
        apiClient.getTvShowDetails(tvShowId, new Callback<TvShowDetailsEntity>() {
            @Override
            public void onResponse(Call<TvShowDetailsEntity> call, Response<TvShowDetailsEntity> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<TvShowDetailsEntity> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }
}
