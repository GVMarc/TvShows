package com.gvmarc.tvshows.presentation.splash;

import com.gvmarc.tvshows.data.entity.config.ConfigurationEntity;

public interface SplashScreenView {
    void saveApiConfiguration(ConfigurationEntity configuration);
    void showNetworkError();
}
