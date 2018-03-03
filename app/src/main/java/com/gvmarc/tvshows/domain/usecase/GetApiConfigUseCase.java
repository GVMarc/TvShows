package com.gvmarc.tvshows.domain.usecase;


import com.gvmarc.tvshows.data.api.TheMovieDbApiClient;
import com.gvmarc.tvshows.data.entity.config.ConfigurationEntity;

import retrofit2.Callback;

public class GetApiConfigUseCase {

    private TheMovieDbApiClient apiClient;

    public GetApiConfigUseCase() {
        apiClient = new TheMovieDbApiClient();
    }

    public void onConfigurationRequested(Callback<ConfigurationEntity> callback) {
        apiClient.getConfiguration(callback);
    }
}
