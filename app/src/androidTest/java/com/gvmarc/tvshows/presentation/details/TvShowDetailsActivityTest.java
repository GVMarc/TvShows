package com.gvmarc.tvshows.presentation.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.gvmarc.tvshows.R;
import com.gvmarc.tvshows.data.entity.list.TvShowEntity;
import com.gvmarc.tvshows.data.entity.list.TvShowListEntity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class TvShowDetailsActivityTest {

    @Rule
    public ActivityTestRule<TvShowDetailsActivity> rule =
            new ActivityTestRule<TvShowDetailsActivity>(TvShowDetailsActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Context context = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();

                    Intent intent = TvShowDetailsActivity.buildIntent(
                            context, 1, "name");
                    return intent;
                }
            };

    private TvShowDetailsActivity activity;

    @Before
    public void setUp() {
        activity = rule.getActivity();
    }

    @Test
    public void buildIntent() {
        Context context = InstrumentationRegistry.getInstrumentation()
                .getTargetContext();

        Intent intent = TvShowDetailsActivity.buildIntent(
                context, 1, "name");
        assertNotNull(intent);

        Bundle extras = intent.getExtras();
        int tvShowId = extras.getInt("tvshow_id");
        String tvShowName = extras.getString("tvshow_name");

        assertEquals(tvShowId, 1);
        assertEquals(tvShowName, "name");

        context.startActivity(intent);
    }

    @Test
    public void buildIntentNull() {
        Intent intent = TvShowDetailsActivity.buildIntent(
                null, 1, "name");

        assertNull(intent);
    }

    @Test
    public void buildIntentUnvalidValues() {
        Context context = InstrumentationRegistry.getInstrumentation()
                .getTargetContext();

        Intent intent = TvShowDetailsActivity.buildIntent(
                context, -10, null);

        assertNotNull(intent);

        Bundle extras = intent.getExtras();
        int tvShowId = extras.getInt("tvshow_id");
        String tvShowName = extras.getString("tvshow_name");

        assertEquals(tvShowId, -10);
        assertEquals(tvShowName, null);

        context.startActivity(intent);
    }

    @Test
    public void onSupportNavigateUp() {
        activity.onSupportNavigateUp();
        assertTrue(activity.isFinishing());
    }

    @Test
    @UiThreadTest
    public void showDetails() {
        TvShowEntity tvShow = new TvShowEntity();
        tvShow.setId(1);
        tvShow.setOverview("overview");
        tvShow.setBackdropPath("/image_path");
        tvShow.setVoteAverage(8.9);
        tvShow.setNumberOfSeasons(3);
        tvShow.setNumberOfEpisodes(40);

        activity.showDetails(tvShow);
    }

    @Test
    @UiThreadTest
    public void showDetailsNull() {
        activity.showDetails(null);
    }

    @Test
    @UiThreadTest
    public void showDetailsEmpty() {
        activity.showDetails(new TvShowEntity());
    }


    @Test
    @UiThreadTest
    public void showSimilarTvShows() {
        List<TvShowEntity> tvShowList = new ArrayList<>();
        tvShowList.add(new TvShowEntity());
        tvShowList.add(new TvShowEntity());

        TvShowListEntity tvShowListEntity = new TvShowListEntity();
        tvShowListEntity.setResults(tvShowList);

        activity.showSimilarTvShows(tvShowListEntity);
    }

    @Test
    public void showSimilarTvShowsNull() {
        activity.showSimilarTvShows(null);
    }

    @Test
    public void showSimilarTvShowsEmpty() {
        activity.showSimilarTvShows(new TvShowListEntity());
    }

    @Test
    public void showNetworkError() {
        activity.showNetworkError();
        onView(withId(R.id.loading)).check(matches(not(isDisplayed())));
        onView(withText("Connection error")).check(matches(isDisplayed()));
    }

}