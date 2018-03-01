package com.gvmarc.tvshows.util;


import com.gvmarc.tvshows.data.entity.list.TvShowEntity;

import java.util.Collections;
import java.util.List;

public class ImageUtil {

    public enum Type {
        POSTER,
        BACKDROP
    }

    public enum Resolution {
        MAX(0),
        HIGH(1),
        MID(2),
        LOW(3);

        private int mIndex;

        Resolution(int index) {
            mIndex = index;
        }

        public int getIndex() {
            return mIndex;
        }
    }

    public static String getImageUrl(TvShowEntity tvShow, Type type) {
        switch (type) {
            case POSTER:
                return AppConstants.IMAGES_BASE_URL +
                        AppConstants.IMAGES_POSTER_SIZE + tvShow.getPosterPath();
            case BACKDROP:
            default:
                return AppConstants.IMAGES_BASE_URL +
                        AppConstants.IMAGES_BACKDROP_SIZE + tvShow.getBackdropPath();
        }
    }

    public static String getAvailableResolution(List<String> sizeList, Resolution resolution) {
        Collections.reverse(sizeList);
        return sizeList.get(resolution.getIndex());
    }

    /**
     * Method to get the proportional height for a view using the
     * view sizes and the parent view width.
     *
     * @param viewHeight  Height of the view
     * @param viewWidth   Width of the view
     * @param parentWidth Width of the parent view
     * @return (int) proportional height for the view
     * @throws ArithmeticException if view width is 0 due to divide per 0
     */
    public static int getProportionalHeight(
            double viewWidth, double viewHeight, double parentWidth)
            throws ArithmeticException {

        double proportion = parentWidth / viewWidth;
        long scaledHeight = Math.round(viewHeight * proportion);
        return (int) scaledHeight;
    }

}