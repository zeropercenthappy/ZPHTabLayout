package com.zeropercenthappy.tablayoutlibrary;

import android.widget.ImageView;

/**
 * @author ybq
 * @date 2017/12/9
 */

public class ZPHTabLayoutConfig {
    private final static int CENTER = 0;
    private final static int CENTER_CROP = 1;
    private final static int CENTER_INSIDE = 2;
    private final static int FIT_CENTER = 3;
    private final static int FIT_END = 4;
    private final static int FIT_START = 5;
    private final static int FIT_XY = 6;
    private final static int MATRIX = 7;

    static void setScaleType(ImageView imageView, int scaleType) {
        switch (scaleType) {
            case CENTER:
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                break;
            case CENTER_CROP:
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                break;
            case CENTER_INSIDE:
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                break;
            case FIT_CENTER:
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                break;
            case FIT_END:
                imageView.setScaleType(ImageView.ScaleType.FIT_END);
                break;
            case FIT_START:
                imageView.setScaleType(ImageView.ScaleType.FIT_START);
                break;
            case FIT_XY:
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                break;
            case MATRIX:
                imageView.setScaleType(ImageView.ScaleType.MATRIX);
                break;
            default:
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                break;
        }
    }
}
