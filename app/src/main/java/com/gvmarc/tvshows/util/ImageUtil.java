package com.gvmarc.tvshows.util;


import java.util.List;

public class ImageUtil {

    public enum Size {
        COVER,
        ORIGINAL
    }

    public static String getImageBaseUrl(Size size) {
        switch (size) {
            case COVER:
                return AppConstants.IMAGES_BASE_URL + AppConstants.IMAGES_COVER_SIZE;
            case ORIGINAL:
            default:
                return AppConstants.IMAGES_BASE_URL + AppConstants.IMAGES_ORIGINAL_SIZE;
        }
    }

    public static String getCoverSize(List<String> sizeList) {
        int midResolution = 4;
        return sizeList.get(midResolution);
    }

    public static String getOriginalSize(List<String> sizeList) {
        int highResolution = 5;
        return sizeList.get(highResolution);
    }

}
