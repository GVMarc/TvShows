package com.gvmarc.tvshows.domain.usecase;

import com.gvmarc.tvshows.data.api.TheMovieDbApiClient;
import com.gvmarc.tvshows.data.entity.TvShowListEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetTvShowsUseCase {

    private TheMovieDbApiClient apiClient;
    private Callback callback;

    public GetTvShowsUseCase(Callback callback) {
        this.callback = callback;
        apiClient = new TheMovieDbApiClient();
    }

    public void onTvShowsRequested(int page) {
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

}
