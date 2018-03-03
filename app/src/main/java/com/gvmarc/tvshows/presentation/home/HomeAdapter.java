package com.gvmarc.tvshows.presentation.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gvmarc.tvshows.R;
import com.gvmarc.tvshows.data.entity.list.TvShowEntity;
import com.gvmarc.tvshows.presentation.base.Navigator;
import com.gvmarc.tvshows.presentation.base.animation.RateBarAnimation;
import com.gvmarc.tvshows.util.ImageUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<ViewHolder> {

    public enum Type {
        MAIN,
        DETAILS
    }

    private List<TvShowEntity> mTvShowList;
    private Type mType;

    public HomeAdapter(List<TvShowEntity> tvShowList, Type type) {
        mTvShowList = tvShowList;
        mType = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        @LayoutRes int layout = mType == Type.MAIN ?
                R.layout.view_tv_show_main : R.layout.view_tv_show_details;

        View view = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);

        return new TvShowViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mTvShowList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final TvShowEntity tvShow = mTvShowList.get(position);

        TvShowViewHolder tvShowViewHolder = (TvShowViewHolder) holder;

        Context context = tvShowViewHolder.view.getContext();

        tvShowViewHolder.title.setText(tvShow.getName());

        String imageUrl = ImageUtil.getImageUrl(tvShow, ImageUtil.Type.POSTER);

        if (imageUrl != null) {
            Picasso.with(context).load(imageUrl).into(tvShowViewHolder.cover);
        }

        tvShowViewHolder.voteAverage.setText(String.valueOf(tvShow.getVoteAverage()));

        Double voteAverage = tvShow.getVoteAverage();
        if (voteAverage != null) {
            int normalizedRating = (int) (tvShow.getVoteAverage() * 10);

            RateBarAnimation rateBarAnimation = new RateBarAnimation(
                    tvShowViewHolder.progressBar, normalizedRating);
            rateBarAnimation.startAnimation();
        }

        tvShowViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.navigateToDetails(view.getContext(), tvShow.getId(), tvShow.getName());
            }
        });
    }

    public void setNewList(List<TvShowEntity> tvShowList) {
        mTvShowList = tvShowList;
        notifyDataSetChanged();
    }

    public void addTvShows(List<TvShowEntity> tvShowList) {
        mTvShowList.addAll(tvShowList);
        notifyDataSetChanged();
    }

    static class TvShowViewHolder extends ViewHolder {

        View view;

        @BindView(R.id.cover)
        ImageView cover;

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.vote_average)
        TextView voteAverage;

        @BindView(R.id.vote_average_progressbar)
        ProgressBar progressBar;

        TvShowViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            view = v;
        }
    }
}
