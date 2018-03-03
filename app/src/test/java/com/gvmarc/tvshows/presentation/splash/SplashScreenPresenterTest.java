package com.gvmarc.tvshows.presentation.splash;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SplashScreenPresenterTest {

    @Mock
    private SplashScreenView view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void requestConfiguration() {
        SplashScreenPresenter presenter = new SplashScreenPresenter(view);
        presenter.requestConfiguration();
    }
}