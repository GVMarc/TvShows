package com.gvmarc.tvshows.util;


import java.util.List;

public class ImageUtil {

    public enum Size {
        POSTER_MID,
        BACKDROP_BIG
    }

    public static String getImageBaseUrl(Size size) {
        switch (size) {
            case POSTER_MID:
                return AppConstants.IMAGES_BASE_URL + AppConstants.IMAGES_POSTER_SIZE;
            case BACKDROP_BIG:
            default:
                return AppConstants.IMAGES_BASE_URL + AppConstants.IMAGES_BACKDROP_SIZE;
        }
    }

    public static String getNormalSize(List<String> sizeList) {
        int resolution = sizeList.size() - 2;
        return sizeList.get(resolution);
    }

}
