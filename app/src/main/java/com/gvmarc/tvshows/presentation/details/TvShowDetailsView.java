package com.gvmarc.tvshows.presentation.details;


import com.gvmarc.tvshows.data.entity.list.TvShowEntity;
import com.gvmarc.tvshows.data.entity.list.TvShowListEntity;

public interface TvShowDetailsView {
    void showNetworkError();
    void showDetails(TvShowEntity tvShowDetails);
    void showSimilarTvShows(TvShowListEntity tvShowList);
}
