package com.gvmarc.tvshows.presentation.home;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.SwipeRefreshLayout;

import com.gvmarc.tvshows.R;
import com.gvmarc.tvshows.data.entity.list.TvShowEntity;
import com.gvmarc.tvshows.data.entity.list.TvShowListEntity;
import com.gvmarc.tvshows.util.AppConstants;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class HomeFragmentTest {

    @Rule
    public ActivityTestRule<HomeActivity> rule =
            new ActivityTestRule<HomeActivity>(HomeActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Context context = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();

                    return HomeActivity.buildIntent(context);
                }
            };

    private HomeActivity activity;
    private HomeFragment fragment;

    @Before
    public void setUp() {
        activity = rule.getActivity();
        fragment = activity.getFragment();
        AppConstants.IMAGES_POSTER_SIZE = "w500";
    }

    @Test
    public void newInstance() {
        HomeFragment fragment = HomeFragment.newInstance();
        assertNotNull(fragment);
    }

    @Test
    @UiThreadTest
    public void addTvShows() {
        List<TvShowEntity> tvShowList = new ArrayList<>();
        tvShowList.add(new TvShowEntity());
        tvShowList.add(new TvShowEntity());

        TvShowListEntity tvShowListEntity = new TvShowListEntity();
        tvShowListEntity.setResults(tvShowList);

        fragment.addTvShows(tvShowListEntity);
    }

    @Test
    public void addTvShowsNull() {
        fragment.addTvShows(null);
    }

    @Test
    public void addTvShowsEmpty() {
        fragment.addTvShows(new TvShowListEntity());
    }

    @Test
    public void showNetworkError() {
        fragment.showNetworkError();
        onView(withId(R.id.bottom_loading)).check(matches(not(isDisplayed())));
        onView(withText("Connection error")).check(matches(isDisplayed()));
    }

    @Test
    public void checkRefreshLoading() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.tv_show_grid)).check(matches(isDisplayingAtLeast(50)));

        SwipeRefreshLayout refreshLayout = activity.findViewById(R.id.refresh_layout);

        assertTrue(!refreshLayout.isRefreshing());

        onView(withId(R.id.tv_show_grid)).perform(swipeDown());

        assertTrue(refreshLayout.isRefreshing());
    }
}