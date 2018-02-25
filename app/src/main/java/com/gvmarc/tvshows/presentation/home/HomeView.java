package com.gvmarc.tvshows.presentation.home;

import com.gvmarc.tvshows.data.entity.TvShowListEntity;

public interface HomeView {

    void fillTvShowGrid(TvShowListEntity tvShowListEntity);

    void onError(String message);
}
