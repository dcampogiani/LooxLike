package com.disorder.presentation.utils;

public class CDNImageUrlGenerator implements ImageUrlGenerator {

    private static final String zoom_low_string = "_10_f";
    private static final String zoom_medium_string = "_11_f";
    private static final String zoom_hight_string = "_13_f";

    private static final String file_extension = ".jpg";

    @Override
    public String getUrl(String c10, ZOOM_LEVEL zoomLevel) {
        return "http://cdn.yoox.biz/" + c10.substring(0, 2) + "/" + c10 + getZoomString(zoomLevel) + file_extension;
    }

    private String getZoomString(ZOOM_LEVEL zoom_level) {
        if (zoom_level == ZOOM_LEVEL.HIGHT)
            return zoom_hight_string;
        else if (zoom_level == ZOOM_LEVEL.MEDIUM)
            return zoom_medium_string;
        else return zoom_low_string;
    }
}
