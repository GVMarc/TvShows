package com.gvmarc.tvshows.presentation.home;

import com.gvmarc.tvshows.data.entity.list.TvShowListEntity;
import com.gvmarc.tvshows.domain.usecase.GetTvShowsUseCase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {

    private HomeView mHomeView;
    private GetTvShowsUseCase mGetTvShowsUseCase;

    public HomePresenter(HomeView view) {
        mHomeView = view;
        mGetTvShowsUseCase = new GetTvShowsUseCase();
    }

    public void requestTvShows(int page) {
        mGetTvShowsUseCase.onPopularTvShowsRequested(page, new Callback<TvShowListEntity>() {
            @Override
            public void onResponse(Call<TvShowListEntity> call, Response<TvShowListEntity> response) {
                mHomeView.addTvShows(response.body());
            }

            @Override
            public void onFailure(Call<TvShowListEntity> call, Throwable t) {
                mHomeView.showNetworkError();
            }
        });
    }
}
