package com.gvmarc.tvshows.domain.usecase;

import com.gvmarc.tvshows.data.api.TvShowsApiClient;
import com.gvmarc.tvshows.data.entity.TvShowListEntity;
import com.gvmarc.tvshows.presentation.home.IHomePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetTvShowsUseCase {

    private TvShowsApiClient apiClient;
    private IHomePresenter listener;

    public GetTvShowsUseCase(IHomePresenter listener) {
        this.listener = listener;
        apiClient = new TvShowsApiClient();
    }

    public void onTvShowsRequested(int page) {
        apiClient.getPopularTvShows(page, new Callback<TvShowListEntity>() {
            @Override
            public void onResponse(Call<TvShowListEntity> call, Response<TvShowListEntity> response) {
                listener.onTvShowsResponse(response.body());
            }

            @Override
            public void onFailure(Call<TvShowListEntity> call, Throwable t) {
                listener.onTvShowsFailure(t.getMessage());
            }
        });
    }

}
