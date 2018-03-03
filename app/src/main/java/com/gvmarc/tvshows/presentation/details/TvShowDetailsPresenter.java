package com.gvmarc.tvshows.presentation.details;

import com.gvmarc.tvshows.data.entity.list.TvShowEntity;
import com.gvmarc.tvshows.data.entity.list.TvShowListEntity;
import com.gvmarc.tvshows.domain.usecase.GetTvShowDetailsUseCase;
import com.gvmarc.tvshows.domain.usecase.GetTvShowsUseCase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowDetailsPresenter {

    private TvShowDetailsView mTvShowDetailsView;
    private GetTvShowDetailsUseCase mGetTvShowDetailsUseCase;
    private GetTvShowsUseCase mGetTvShowsUseCase;

    public TvShowDetailsPresenter(TvShowDetailsView view) {
        mTvShowDetailsView = view;
        mGetTvShowDetailsUseCase = new GetTvShowDetailsUseCase();
        mGetTvShowsUseCase = new GetTvShowsUseCase();
    }

    public void requestTvShowDetails(int tvShowId) {
        mGetTvShowDetailsUseCase.onDetailsRequested(tvShowId, new Callback<TvShowEntity>() {
            @Override
            public void onResponse(Call<TvShowEntity> call, Response<TvShowEntity> response) {
                mTvShowDetailsView.showDetails(response.body());
            }

            @Override
            public void onFailure(Call<TvShowEntity> call, Throwable t) {
                mTvShowDetailsView.showNetworkError();
            }
        });
    }

    public void requestSimilarTvShows(int tvShowId) {
        mGetTvShowsUseCase.onSimilarTvShowsRequested(tvShowId, new Callback<TvShowListEntity>() {
            @Override
            public void onResponse(Call<TvShowListEntity> call, Response<TvShowListEntity> response) {
                mTvShowDetailsView.showSimilarTvShows(response.body());
            }

            @Override
            public void onFailure(Call<TvShowListEntity> call, Throwable t) {
                mTvShowDetailsView.showNetworkError();
            }
        });
    }
}


