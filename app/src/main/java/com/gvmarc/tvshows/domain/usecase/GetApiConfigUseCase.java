package com.gvmarc.tvshows.domain.usecase;


import com.gvmarc.tvshows.data.api.TheMovieDbApiClient;
import com.gvmarc.tvshows.data.entity.config.ConfigurationEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetApiConfigUseCase {

    private TheMovieDbApiClient apiClient;

    public GetApiConfigUseCase() {
        apiClient = new TheMovieDbApiClient();
    }

    public void onConfigurationRequested(final Callback<ConfigurationEntity> callback) {
        apiClient.getConfiguration(new Callback<ConfigurationEntity>() {
            @Override
            public void onResponse(Call<ConfigurationEntity> call, Response<ConfigurationEntity> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<ConfigurationEntity> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }
}
