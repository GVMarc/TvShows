package com.gvmarc.tvshows.presentation.home;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class HomePresenterTest {

    @Mock
    private HomeView homeView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void requestTvShows() {
        HomePresenter presenter = new HomePresenter(homeView);
        presenter.requestTvShows(2);
    }

    @Test
    public void requestTvShowsNegativeValue() {
        HomePresenter presenter = new HomePresenter(homeView);
        presenter.requestTvShows(-10);
    }

}