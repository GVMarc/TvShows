package com.gvmarc.tvshows.presentation.details;


import com.gvmarc.tvshows.data.entity.details.TvShowDetailsEntity;

public interface TvShowDetailsView {
    void showNetworkError();
    void showDetails(TvShowDetailsEntity tvShowDetails);
}
