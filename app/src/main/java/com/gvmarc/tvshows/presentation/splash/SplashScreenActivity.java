package com.gvmarc.tvshows.presentation.splash;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gvmarc.tvshows.R;
import com.gvmarc.tvshows.data.entity.config.ConfigurationEntity;
import com.gvmarc.tvshows.data.entity.config.ImagesConfigEntity;
import com.gvmarc.tvshows.presentation.base.Navigator;
import com.gvmarc.tvshows.util.AppConstants;
import com.gvmarc.tvshows.util.ImageUtil;

import butterknife.ButterKnife;

public class SplashScreenActivity extends AppCompatActivity implements SplashScreenView {

    @LayoutRes
    private final int layout = R.layout.activity_splash_screen;

    private static final int MIN_SPLASH_TIME = 2 * 1000;

    private boolean mTimeIsReady;
    private boolean mConfigIsReady;

    private SplashScreenPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout);
        ButterKnife.bind(this);
        initTimer();
        mPresenter = new SplashScreenPresenter(this);
        requestConfiguration();
    }

    private void initTimer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mTimeIsReady = true;
                checkIsReadyToNavigate();
            }
        }, MIN_SPLASH_TIME);
    }

    private void requestConfiguration() {
        mPresenter.requestConfiguration();
    }

    @Override
    public void saveApiConfiguration(ConfigurationEntity configuration) {
        ImagesConfigEntity imagesConfig = configuration.getImages();

        AppConstants.IMAGES_BASE_URL = imagesConfig.getSecureBaseUrl();
        AppConstants.IMAGES_POSTER_SIZE = ImageUtil.getAvailableResolution(
                imagesConfig.getPosterSizes(), ImageUtil.Resolution.MID);
        AppConstants.IMAGES_BACKDROP_SIZE = ImageUtil.getAvailableResolution(
                imagesConfig.getBackdropSizes(), ImageUtil.Resolution.HIGH);

        mConfigIsReady = true;
        checkIsReadyToNavigate();
    }

    @Override
    public void showNetworkError() {
        mConfigIsReady = true;
        checkIsReadyToNavigate();
    }

    private void checkIsReadyToNavigate() {
        if (mTimeIsReady && mConfigIsReady) {
            Navigator.navigateToHome(SplashScreenActivity.this);
        }
    }
}
