package com.gvmarc.tvshows.presentation.splash;


import com.gvmarc.tvshows.data.entity.config.ConfigurationEntity;
import com.gvmarc.tvshows.domain.usecase.GetApiConfigUseCase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashScreenPresenter implements Callback<ConfigurationEntity> {

    private SplashScreenView mSplashScreenView;
    private GetApiConfigUseCase mGetApiConfigUseCase;

    public SplashScreenPresenter(SplashScreenView view) {
        mSplashScreenView = view;
        mGetApiConfigUseCase = new GetApiConfigUseCase(this);
    }

    public void requestConfiguration() {
        mGetApiConfigUseCase.onConfigurationRequested();
    }

    @Override
    public void onResponse(Call<ConfigurationEntity> call, Response<ConfigurationEntity> response) {
        mSplashScreenView.saveApiConfiguration(response.body());
    }

    @Override
    public void onFailure(Call<ConfigurationEntity> call, Throwable t) {
        mSplashScreenView.showNetworkError();
    }
}
