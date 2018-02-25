package com.gvmarc.tvshows.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gvmarc.tvshows.R;
import com.gvmarc.tvshows.data.entity.TvShowEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TvShowEntity> mTvShowList;

    public TvShowAdapter(List<TvShowEntity> tvShowList) {
        mTvShowList = tvShowList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_tv_show, parent, false);

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

        tvShowViewHolder.title.setText(tvShow.getName());
    }

    public void setNewList(List<TvShowEntity> tvShowList) {
        mTvShowList = tvShowList;
        notifyDataSetChanged();
    }

    public void addTvShows(List<TvShowEntity> tvShowList) {
        mTvShowList.addAll(tvShowList);
        notifyDataSetChanged();
    }

    static class TvShowViewHolder extends RecyclerView.ViewHolder {

        View view;

        @BindView(R.id.cover)
        ImageView cover;

        @BindView(R.id.title)
        TextView title;

        TvShowViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            view = v;
        }
    }
}
