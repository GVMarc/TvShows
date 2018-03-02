package com.gvmarc.tvshows.presentation.details;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.gvmarc.tvshows.BuildConfig;
import com.gvmarc.tvshows.R;
import com.gvmarc.tvshows.data.entity.list.TvShowEntity;
import com.gvmarc.tvshows.data.entity.list.TvShowListEntity;
import com.gvmarc.tvshows.presentation.base.animation.HeightAnimation;
import com.gvmarc.tvshows.presentation.home.HomeAdapter;
import com.gvmarc.tvshows.util.ImageUtil;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

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
    @BindView(R.id.similar_section)
    View mSimilarTvShowsSection;
    @BindView(R.id.tv_show_grid)
    RecyclerView mSimilarTvShowsRecyclerView;
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

    private LinearLayoutManager mLayoutManager;

    private int mTvShowId;
    private String mTvShowName;

    private TvShowDetailsPresenter mTvShowDetailsPresenter;

    private Target mImageTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            int height = bitmap.getHeight();
            int width = bitmap.getWidth();
            int parentWidth = mToolbar.getWidth();
            try {
                int proportionalHeight = ImageUtil.getProportionalHeight(
                        width, height, parentWidth);

                if (parentWidth != width) {
                    bitmap = Bitmap.createScaledBitmap(
                            bitmap, parentWidth, proportionalHeight, false);
                }

                mImage.setImageBitmap(bitmap);

                HeightAnimation heightAnimation = new HeightAnimation(mImage, proportionalHeight);
                heightAnimation.startAnimation();

            } catch (ArithmeticException e) {
                mImage.setImageBitmap(bitmap);
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            }
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
        requestContent();
    }

    private void requestContent() {
        mTvShowDetailsPresenter.requestTvShowDetails(mTvShowId);
        mTvShowDetailsPresenter.requestSimilarTvShows(mTvShowId);
    }

    private void getArguments() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(EXTRA_TVSHOW_ID)) {
            mTvShowId = bundle.getInt(EXTRA_TVSHOW_ID);
            mTvShowName = bundle.getString(EXTRA_TVSHOW_NAME);
        }
    }

    private void initViews() {
        setLoading(true);
        initToolbar();
        initRecyclerView();
    }

    private void initToolbar() {
        setTitle(mTvShowName);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initRecyclerView() {
        mSimilarTvShowsRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false);
        mSimilarTvShowsRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void animateContent() {
        mContentLayout.setVisibility(View.VISIBLE);
        Animation contentAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        mContentLayout.startAnimation(contentAnimation);
    }

    private void setLoading(boolean loading) {
        if (loading) {
            mLoading.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.loading);
            mLoading.startAnimation(animation);
        } else {
            mLoading.setVisibility(View.GONE);
            mLoading.clearAnimation();
        }
    }

    @Override
    public void showDetails(TvShowEntity tvShow) {
        setLoading(false);
        String seasons = String.valueOf(tvShow.getNumberOfSeasons());
        String episodes = String.valueOf(tvShow.getNumberOfEpisodes());
        String voteAverage = String.valueOf(tvShow.getVoteAverage());

        mOverview.setText(tvShow.getOverview());
        mSeasons.setText(seasons);
        mEpisodes.setText(episodes);
        mVoteAverage.setText(voteAverage);

        animateContent();

        String imageUrl = ImageUtil.getImageUrl(tvShow, ImageUtil.Type.BACKDROP);

        if (imageUrl != null) {
            Picasso.with(this).load(imageUrl).into(mImageTarget);
        }
    }

    @Override
    public void showSimilarTvShows(TvShowListEntity tvShowListEntity) {
        if (tvShowListEntity != null) {
            List<TvShowEntity> tvShowList = tvShowListEntity.getResults();
            if (tvShowList != null && !tvShowList.isEmpty()) {
                mSimilarTvShowsSection.setVisibility(View.VISIBLE);
                HomeAdapter homeAdapter = new HomeAdapter(tvShowList, HomeAdapter.Type.DETAILS);
                mSimilarTvShowsRecyclerView.setAdapter(homeAdapter);
            }
        }
    }

    @Override
    public void showNetworkError() {
        setLoading(false);
    }
}
