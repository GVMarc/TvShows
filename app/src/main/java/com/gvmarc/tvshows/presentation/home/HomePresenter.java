package com.gvmarc.tvshows.presentation.home;

import com.gvmarc.tvshows.data.entity.TvShowListEntity;
import com.gvmarc.tvshows.domain.usecase.GetTvShowsUseCase;

public class HomePresenter implements IHomePresenter {

    private HomeView homeView;
    private GetTvShowsUseCase getTvShowsUseCase;

    public HomePresenter(HomeView homeView) {
        this.homeView = homeView;
        getTvShowsUseCase = new GetTvShowsUseCase(this);
    }

    public void requestTvShows(int page) {
        getTvShowsUseCase.onTvShowsRequested(page);
    }


    @Override
    public void onTvShowsResponse(TvShowListEntity tvShowListEntity) {
        homeView.fillTvShowGrid(tvShowListEntity);
    }

    @Override
    public void onTvShowsFailure(String message) {
        homeView.onError(message);
    }
}
