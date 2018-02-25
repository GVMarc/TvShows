package com.gvmarc.tvshows.presentation.details;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.gvmarc.tvshows.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowDetailsActivity extends AppCompatActivity {

    @LayoutRes
    private final int layout = R.layout.activity_tvshow_details;

    private static final String EXTRA_TVSHOW_ID = "tvshow_id";
    private static final String EXTRA_TVSHOW_NAME = "tvshow_name";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.title)
    TextView mTitle;

    private int mTvShowId;
    private String mTvShowName;

    public static Intent buildIntent(Context context, int tvShowId, String tvShowName) {
        Intent intent = new Intent(context, TvShowDetailsActivity.class);
        intent.putExtra(EXTRA_TVSHOW_ID, tvShowId);
        intent.putExtra(EXTRA_TVSHOW_NAME, tvShowName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout);
        ButterKnife.bind(this);

        getArguments();
        initViews();
    }

    private void getArguments() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(EXTRA_TVSHOW_ID)) {
            mTvShowId = bundle.getInt(EXTRA_TVSHOW_ID);
            mTvShowName = bundle.getString(EXTRA_TVSHOW_NAME);
        }
    }

    private void initViews() {
        initToolbar();
        mTitle.setText("" + mTvShowId);
    }

    private void initToolbar() {
        setTitle(mTvShowName);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
