package com.gvmarc.tvshows.presentation.splash;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.gvmarc.tvshows.data.entity.config.ConfigurationEntity;
import com.gvmarc.tvshows.data.entity.config.ImagesConfigEntity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class SplashScreenActivityTest {

    @Rule
    public ActivityTestRule<SplashScreenActivity> rule =
            new ActivityTestRule<>(SplashScreenActivity.class);

    private SplashScreenActivity activity;

    @Before
    public void setUp() {
        activity = rule.getActivity();
    }

    @Test
    public void saveApiConfiguration() {
        ConfigurationEntity configuration = new ConfigurationEntity();
        ImagesConfigEntity imagesConfig = new ImagesConfigEntity();

        List<String> resolutionList = new ArrayList<>();
        resolutionList.add("w100");
        resolutionList.add("w200");
        resolutionList.add("w300");
        resolutionList.add("w400");
        resolutionList.add("w500");

        imagesConfig.setBackdropSizes(resolutionList);
        imagesConfig.setPosterSizes(resolutionList);

        configuration.setImages(imagesConfig);

        activity.saveApiConfiguration(configuration);
    }

    @Test
    public void saveApiConfigurationNull() {
        activity.saveApiConfiguration(null);
    }

    @Test
    public void saveApiConfigurationEmpty() {
        activity.saveApiConfiguration(new ConfigurationEntity());
    }

    @Test
    public void showNetworkError() {
        activity.showNetworkError();
    }

}