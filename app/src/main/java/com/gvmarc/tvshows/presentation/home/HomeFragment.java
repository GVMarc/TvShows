package com.gvmarc.tvshows.presentation.home;

import android.content.res.Configuration;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.gvmarc.tvshows.R;
import com.gvmarc.tvshows.data.entity.list.TvShowEntity;
import com.gvmarc.tvshows.data.entity.list.TvShowListEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment implements HomeView {

    @LayoutRes
    private final int layout = R.layout.fragment_home;

    private final static int PHONE_COLUMNS = 2;
    private final static int TABLET_COLUMNS = 3;
    private final static int FIRST_PAGE = 1;
    private final static int LAST_ROWS_TO_LOAD_MORE_TVSHOWS = 3;

    public enum LoadingType {
        REFRESH,
        BOTTOM
    }

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    @BindView(R.id.tv_show_grid)
    RecyclerView mTvShowRecyclerView;

    @BindView(R.id.bottom_loading)
    View mBottomLoading;

    private int mPagination = FIRST_PAGE;
    private int mColumns;

    private boolean mIsLoading;

    private HomePresenter mPresenter;
    private StaggeredGridLayoutManager mLayoutManager;
    private HomeAdapter mHomeAdapter;

    private Animation mBottomLoadingAnimation;

    public static HomeFragment newInstance() {
        return new HomeFragment();
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
        setLoading(true, LoadingType.REFRESH);

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
                onTvShowGridRefreshed();
            }
        });
    }


    private void initRecyclerView() {
        mTvShowRecyclerView.setHasFixedSize(true);

        mColumns = PHONE_COLUMNS;
        boolean isTablet = getResources().getBoolean(R.bool.is_tablet);
        if (isTablet) {
            mColumns = TABLET_COLUMNS;
        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mColumns *= 2;
        }

        mLayoutManager = new StaggeredGridLayoutManager(mColumns, OrientationHelper.VERTICAL);
        mTvShowRecyclerView.setLayoutManager(mLayoutManager);

        mTvShowRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    onTvShowGridScrolled();
                }
            }
        });
    }

    private void onTvShowGridScrolled() {
        int totalItemCount = mLayoutManager.getItemCount();
        int[] lastVisibleItems = new int[mColumns];
        mLayoutManager.findLastVisibleItemPositions(lastVisibleItems);

        int lastVisibleItemsToLoad = mColumns * LAST_ROWS_TO_LOAD_MORE_TVSHOWS;

        if (!mIsLoading && totalItemCount <= lastVisibleItems[0] + lastVisibleItemsToLoad) {
            requestTvShows();
            setLoading(true, LoadingType.BOTTOM);
        }
    }

    private void onTvShowGridRefreshed() {
        resetPagination();
        requestTvShows();
        setLoading(true, LoadingType.REFRESH);
    }

    private void requestTvShows() {
        mPresenter.requestTvShows(mPagination);
        mPagination++;
    }

    @Override
    public void addTvShows(TvShowListEntity tvShowListEntity) {

        LoadingType loadingType = LoadingType.REFRESH;
        if (tvShowListEntity != null) {
            List<TvShowEntity> tvShowList = tvShowListEntity.getResults();
            if (tvShowList != null && !tvShowList.isEmpty()) {
                if (mHomeAdapter == null) {
                    mHomeAdapter = new HomeAdapter(tvShowList, HomeAdapter.Type.MAIN);
                    mTvShowRecyclerView.setAdapter(mHomeAdapter);
                } else if (tvShowListEntity.getPage() == FIRST_PAGE) {
                    mHomeAdapter.setNewList(tvShowList);
                } else {
                    mHomeAdapter.addTvShows(tvShowList);
                    loadingType = LoadingType.BOTTOM;
                }
            }
        }
        setLoading(false, loadingType);
    }

    @Override
    public void showNetworkError() {
        Snackbar.make(mRefreshLayout, R.string.connection_error, Snackbar.LENGTH_LONG).show();
        setLoading(false, LoadingType.REFRESH);
        setLoading(false, LoadingType.BOTTOM);
    }

    private void setLoading(boolean loading, LoadingType loadingType) {
        mIsLoading = loading;

        switch (loadingType) {
            case REFRESH:
                showRefreshLayoutLoading(loading);
                break;
            case BOTTOM:
                if (loading) showBottomLoading();
                break;
        }
    }

    private void showBottomLoading() {
        if (mBottomLoadingAnimation == null) {
            mBottomLoadingAnimation = AnimationUtils.loadAnimation(
                    getActivity(), R.anim.bottom_loading);
            mBottomLoading.setVisibility(View.VISIBLE);
        }

        mBottomLoading.startAnimation(mBottomLoadingAnimation);
    }

    private void showRefreshLayoutLoading(boolean show) {
        if (mRefreshLayout != null) {
            mRefreshLayout.setRefreshing(show);
        }
    }

    private void resetPagination() {
        mPagination = FIRST_PAGE;
    }
}
