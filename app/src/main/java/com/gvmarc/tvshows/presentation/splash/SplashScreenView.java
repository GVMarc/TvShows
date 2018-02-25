package com.gvmarc.tvshows.presentation.splash;

import com.gvmarc.tvshows.data.entity.ConfigurationEntity;

public interface SplashScreenView {

    void saveApiConfiguration(ConfigurationEntity configuration);

    void onNetworkError();
}
