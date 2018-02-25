package com.gvmarc.tvshows.presentation.home;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gvmarc.tvshows.R;
import com.gvmarc.tvshows.data.entity.TvShowEntity;
import com.gvmarc.tvshows.data.entity.TvShowListEntity;
import com.gvmarc.tvshows.presentation.adapter.TvShowAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment implements HomeView {

    @LayoutRes
    int layout = R.layout.fragment_home;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_show_grid)
    RecyclerView mTvShowRecyclerView;

    int page = 1;

    private HomePresenter mPresenter;

    private StaggeredGridLayoutManager mLayoutManager;
    private TvShowAdapter mTvShowAdapter;

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(layout, container, false);
        ButterKnife.bind(this, view);

        initViews();

        mPresenter = new HomePresenter(this);
        requestTvShows();

        return view;
    }

    private void initViews() {
        initRefreshLayout();
        initRecyclerView();
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.accent),
                ContextCompat.getColor(getActivity(), R.color.primary),
                ContextCompat.getColor(getActivity(), R.color.primaryDark));

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //TODO
            }
        });
    }


    private void initRecyclerView() {
        mLayoutManager = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
        mTvShowRecyclerView.setLayoutManager(mLayoutManager);

        mTvShowRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //TODO
            }
        });
    }

    private void requestTvShows() {
        mPresenter.requestTvShows(page);
    }

    @Override
    public void fillTvShowGrid(TvShowListEntity tvShowListEntity) {

        if (tvShowListEntity != null) {

            List<TvShowEntity> tvShowList = tvShowListEntity.getResults();

            if (tvShowList != null && !tvShowList.isEmpty())
                if (mTvShowAdapter == null) {
                    mTvShowAdapter = new TvShowAdapter(tvShowList);
                    mTvShowRecyclerView.setAdapter(mTvShowAdapter);
                } else {
                    mTvShowAdapter.setNewList(tvShowList);
                }
        }
    }

    @Override
    public void onError(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_INDEFINITE).show();
    }
}
