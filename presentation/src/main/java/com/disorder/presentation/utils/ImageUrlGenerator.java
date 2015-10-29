package com.disorder.presentation.utils;

public interface ImageUrlGenerator {

    enum ZOOM_LEVEL {
        LOW, MEDIUM, HIGHT
    }

    String getUrl(String c10, ZOOM_LEVEL zoom_level);
}
