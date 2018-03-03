package com.gvmarc.tvshows.presentation.base;


import android.content.Context;

import com.gvmarc.tvshows.presentation.details.TvShowDetailsActivity;
import com.gvmarc.tvshows.presentation.home.HomeActivity;

public class Navigator {

    public static void navigateToHome(Context context) {
        context.startActivity(HomeActivity.buildIntent(context));
    }

    public static void navigateToDetails(Context context, int tvShowId, String tvShowName) {
        context.startActivity(TvShowDetailsActivity.buildIntent(context, tvShowId, tvShowName));
    }
}
