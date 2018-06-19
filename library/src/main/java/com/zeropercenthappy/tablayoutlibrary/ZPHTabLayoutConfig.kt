package com.zeropercenthappy.tablayoutlibrary

import android.widget.ImageView

/**
 * @author ybq
 * @date 2017/12/9
 */

object ZPHTabLayoutConfig {
    private const val CENTER = 0
    private const val CENTER_CROP = 1
    private const val CENTER_INSIDE = 2
    private const val FIT_CENTER = 3
    private const val FIT_END = 4
    private const val FIT_START = 5
    private const val FIT_XY = 6
    private const val MATRIX = 7

    internal fun setScaleType(imageView: ImageView, scaleType: Int) {
        when (scaleType) {
            CENTER -> imageView.scaleType = ImageView.ScaleType.CENTER
            CENTER_CROP -> imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            CENTER_INSIDE -> imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
            FIT_CENTER -> imageView.scaleType = ImageView.ScaleType.FIT_CENTER
            FIT_END -> imageView.scaleType = ImageView.ScaleType.FIT_END
            FIT_START -> imageView.scaleType = ImageView.ScaleType.FIT_START
            FIT_XY -> imageView.scaleType = ImageView.ScaleType.FIT_XY
            MATRIX -> imageView.scaleType = ImageView.ScaleType.MATRIX
            else -> imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
        }
    }
}
