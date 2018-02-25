package com.gvmarc.tvshows.presentation.details;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.gvmarc.tvshows.R;
import com.gvmarc.tvshows.data.entity.details.TvShowDetailsEntity;
import com.gvmarc.tvshows.util.ImageUtil;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowDetailsActivity extends AppCompatActivity implements TvShowDetailsView {

    @LayoutRes
    private final int layout = R.layout.activity_tvshow_details;

    private static final String EXTRA_TVSHOW_ID = "tvshow_id";
    private static final String EXTRA_TVSHOW_NAME = "tvshow_name";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.overview)
    TextView mOverview;

    @BindView(R.id.image)
    ImageView mImage;

    private TvShowDetailsPresenter mTvShowDetailsPresenter;

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
        mTvShowDetailsPresenter = new TvShowDetailsPresenter(this);
        requestTvShowDetails();
    }

    private void requestTvShowDetails() {
        mTvShowDetailsPresenter.requestTvShowDetails(mTvShowId);
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

    @Override
    public void showDetails(TvShowDetailsEntity tvShowDetails) {
        String imageUrl = ImageUtil.getImageBaseUrl(ImageUtil.Size.ORIGINAL)
                + tvShowDetails.getBackdropPath();
        Picasso.with(this).load(imageUrl).placeholder(R.color.accent).into(mImage);
        mOverview.setText(tvShowDetails.getOverview());
    }

    @Override
    public void showNetworkError() {

    }
}
