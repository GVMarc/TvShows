package com.gvmarc.tvshows.domain.usecase;

import com.gvmarc.tvshows.data.api.TheMovieDbApiClient;
import com.gvmarc.tvshows.data.entity.list.TvShowListEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetTvShowsUseCase {

    private TheMovieDbApiClient apiClient;

    public GetTvShowsUseCase() {
        apiClient = new TheMovieDbApiClient();
    }

    public void onPopularTvShowsRequested(int page, final Callback<TvShowListEntity> callback) {
        apiClient.getPopularTvShows(page, new Callback<TvShowListEntity>() {
            @Override
            public void onResponse(Call<TvShowListEntity> call, Response<TvShowListEntity> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<TvShowListEntity> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }

    public void onSimilarTvShowsRequested(int tvShowId, final Callback<TvShowListEntity> callback) {
        apiClient.getSimilarTvShows(tvShowId, new Callback<TvShowListEntity>() {
            @Override
            public void onResponse(Call<TvShowListEntity> call, Response<TvShowListEntity> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<TvShowListEntity> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }
}
