package com.gvmarc.tvshows.presentation.home;

import com.gvmarc.tvshows.data.entity.TvShowListEntity;

public interface HomeView {

    void addTvShowsToGrid(TvShowListEntity tvShowListEntity);
    void onNetworkError();
}
