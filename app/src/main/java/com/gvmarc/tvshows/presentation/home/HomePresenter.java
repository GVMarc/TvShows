package com.gvmarc.tvshows.presentation.home;

import com.gvmarc.tvshows.data.entity.TvShowListEntity;
import com.gvmarc.tvshows.domain.usecase.GetTvShowsUseCase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements Callback<TvShowListEntity> {

    private HomeView mHomeView;
    private GetTvShowsUseCase mGetTvShowsUseCase;

    public HomePresenter(HomeView homeView) {
        mHomeView = homeView;
        mGetTvShowsUseCase = new GetTvShowsUseCase(this);
    }

    public void requestTvShows(int page) {
        mGetTvShowsUseCase.onTvShowsRequested(page);
    }

    @Override
    public void onResponse(Call<TvShowListEntity> call, Response<TvShowListEntity> response) {
        mHomeView.addTvShowsToGrid(response.body());
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        mHomeView.onNetworkError();
    }
}
