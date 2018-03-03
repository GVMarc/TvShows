package com.gvmarc.tvshows.util;

import com.gvmarc.tvshows.data.entity.list.TvShowEntity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class ImageUtilTest {

    @Mock
    private TvShowEntity tvShowEntity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getPosterImageUrl() {
        AppConstants.IMAGES_BASE_URL = "https://www.test.com";
        AppConstants.IMAGES_POSTER_SIZE = "/w500";

        when(tvShowEntity.getPosterPath()).thenReturn("/123abc");

        String url = ImageUtil.getImageUrl(tvShowEntity, ImageUtil.Type.POSTER);

        assertTrue(url.matches(".*/w[0-9]+/.*"));
        assertTrue(url.equals("https://www.test.com/w500/123abc"));
    }

    @Test
    public void getBackdropImageUrl() {
        AppConstants.IMAGES_BASE_URL = "https://www.test.com";
        AppConstants.IMAGES_BACKDROP_SIZE = "/w800";

        when(tvShowEntity.getBackdropPath()).thenReturn("/123abc");

        String url = ImageUtil.getImageUrl(tvShowEntity, ImageUtil.Type.BACKDROP);

        assertTrue(url.matches(".*/w[0-9]+/.*"));
        assertEquals(url, "https://www.test.com/w800/123abc");
    }

    @Test
    public void getAvailableResolution() {
        List<String> resolutionList = new ArrayList<>();
        resolutionList.add("w100");
        resolutionList.add("w200");
        resolutionList.add("w300");
        resolutionList.add("w400");
        resolutionList.add("w500");

        Collections.reverse(resolutionList);

        String resolution = ImageUtil.getAvailableResolution(
                resolutionList, ImageUtil.Resolution.MAX);

        assertEquals(resolution, "w500");

        resolution = ImageUtil.getAvailableResolution(
                resolutionList, ImageUtil.Resolution.HIGH);

        assertEquals(resolution, "w400");

        resolution = ImageUtil.getAvailableResolution(
                resolutionList, ImageUtil.Resolution.MID);

        assertEquals(resolution, "w300");

        resolution = ImageUtil.getAvailableResolution(
                resolutionList, ImageUtil.Resolution.LOW);

        assertEquals(resolution, "w200");
    }

    @Test
    public void getProportionalHeight() {
        int height = ImageUtil.getProportionalHeight(200, 100, 400);
        assertEquals(height, 200);

        height = ImageUtil.getProportionalHeight(500, 250, 250);
        assertEquals(height, 125);
    }
}