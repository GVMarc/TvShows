package com.gvmarc.tvshows.presentation.details;


import com.gvmarc.tvshows.data.entity.list.TvShowEntity;

public interface TvShowDetailsView {
    void showNetworkError();
    void showDetails(TvShowEntity tvShowDetails);
}
