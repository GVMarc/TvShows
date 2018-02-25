package com.gvmarc.tvshows.domain.usecase;


import com.gvmarc.tvshows.data.api.TheMovieDbApiClient;
import com.gvmarc.tvshows.data.entity.ConfigurationEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetApiConfigUseCase {

    private TheMovieDbApiClient apiClient;
    private Callback callback;

    public GetApiConfigUseCase(Callback callback) {
        this.callback = callback;
        apiClient = new TheMovieDbApiClient();
    }

    public void onConfigurationRequested() {
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
