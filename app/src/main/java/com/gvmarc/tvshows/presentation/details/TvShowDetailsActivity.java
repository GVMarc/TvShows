package com.gvmarc.tvshows.presentation.details;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.gvmarc.tvshows.R;
import com.gvmarc.tvshows.data.entity.details.TvShowDetailsEntity;
import com.gvmarc.tvshows.presentation.base.animation.HeightAnimation;
import com.gvmarc.tvshows.util.ImageUtil;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowDetailsActivity extends AppCompatActivity implements TvShowDetailsView {

    @LayoutRes
    private final int layout = R.layout.activity_tvshow_details;

    private static final String EXTRA_TVSHOW_ID = "tvshow_id";
    private static final String EXTRA_TVSHOW_NAME = "tvshow_name";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.content_layout)
    View mContentLayout;
    @BindView(R.id.overview)
    TextView mOverview;
    @BindView(R.id.image)
    ImageView mImage;
    @BindView(R.id.seasons)
    TextView mSeasons;
    @BindView(R.id.episodes)
    TextView mEpisodes;
    @BindView(R.id.vote_average)
    TextView mVoteAverage;
    @BindView(R.id.loading)
    View mLoading;

    private int mTvShowId;
    private String mTvShowName;

    private TvShowDetailsPresenter mTvShowDetailsPresenter;

    private Target mImageTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            mImage.setImageBitmap(bitmap);

            double height = bitmap.getHeight();
            double width = bitmap.getWidth();
            double parentWidth = mToolbar.getWidth();

            HeightAnimation heightAnimation = new HeightAnimation(
                    mImage, height, width, parentWidth);
            heightAnimation.startAnimation();
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
        }
    };

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
        setLoading(false);
        String seasons = String.valueOf(tvShowDetails.getNumberOfSeasons());
        String episodes = String.valueOf(tvShowDetails.getNumberOfEpisodes());
        String voteAverage = String.valueOf(tvShowDetails.getVoteAverage());

        mOverview.setText(tvShowDetails.getOverview());
        mSeasons.setText(seasons);
        mEpisodes.setText(episodes);
        mVoteAverage.setText(voteAverage);

        animateContent();

        String imageUrl = ImageUtil.getImageBaseUrl(ImageUtil.Size.BACKDROP_BIG)
                + tvShowDetails.getBackdropPath();

        Picasso.with(this).load(imageUrl).into(mImageTarget);
    }

    private void animateContent() {
        mContentLayout.setVisibility(View.VISIBLE);
        Animation contentAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        mContentLayout.startAnimation(contentAnimation);
    }

    @Override
    public void showNetworkError() {
        setLoading(false);
    }

    private void setLoading(boolean loading) {
        int visibility = loading ? View.VISIBLE : View.GONE;
        mLoading.setVisibility(visibility);
    }
}
