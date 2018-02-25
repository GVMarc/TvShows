package com.gvmarc.tvshows.presentation.home;


import com.gvmarc.tvshows.data.entity.TvShowListEntity;


public interface IHomePresenter {
    void onTvShowsResponse(TvShowListEntity tvShowListEntity);
    void onTvShowsFailure(String message);
}
