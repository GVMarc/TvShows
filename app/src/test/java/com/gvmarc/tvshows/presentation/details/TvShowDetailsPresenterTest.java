package com.gvmarc.tvshows.presentation.details;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TvShowDetailsPresenterTest {

    @Mock
    private TvShowDetailsView view;

    private TvShowDetailsPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new TvShowDetailsPresenter(view);
    }

    @Test
    public void requestTvShowDetails() throws Exception {
        presenter.requestTvShowDetails(1);
    }

    @Test
    public void requestSimilarTvShows() throws Exception {
        presenter.requestSimilarTvShows(1);
    }

}