package com.gvmarc.tvshows.presentation.home;

import com.gvmarc.tvshows.data.entity.list.TvShowListEntity;

public interface HomeView {
    void addTvShows(TvShowListEntity tvShowListEntity);
    void showNetworkError();
}
