package com.gvmarc.tvshows.presentation.details;


import com.gvmarc.tvshows.data.entity.list.TvShowEntity;
import com.gvmarc.tvshows.domain.usecase.GetTvShowDetailsUseCase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowDetailsPresenter implements Callback<TvShowEntity> {

    private TvShowDetailsView mTvShowDetailsView;
    private GetTvShowDetailsUseCase mGetTvShowDetailsUseCase;

    public TvShowDetailsPresenter(TvShowDetailsView view) {
        mTvShowDetailsView = view;
        mGetTvShowDetailsUseCase = new GetTvShowDetailsUseCase(this);
    }

    public void requestTvShowDetails(int tvShowId) {
        mGetTvShowDetailsUseCase.onDetailsRequested(tvShowId);
    }

    @Override
    public void onResponse(Call<TvShowEntity> call, Response<TvShowEntity> response) {
        mTvShowDetailsView.showDetails(response.body());
    }

    @Override
    public void onFailure(Call<TvShowEntity> call, Throwable t) {
        mTvShowDetailsView.showNetworkError();
    }
}
