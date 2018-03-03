package com.gvmarc.tvshows;


import android.app.Application;

import com.squareup.picasso.Picasso;

import timber.log.Timber;

public class App extends Application {

    @Override
    public void onCreate() {
        if (BuildConfig.DEBUG) initDebugTools();
        super.onCreate();
    }

    private void initDebugTools() {
        Timber.plant(new Timber.DebugTree());
        Picasso.with(this).setIndicatorsEnabled(true);
    }
}
