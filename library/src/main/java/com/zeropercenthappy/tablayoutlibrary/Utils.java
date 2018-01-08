package com.zeropercenthappy.tablayoutlibrary;

import android.content.Context;

/**
 * @author ybq
 * @date 2018/1/8
 */

public class Utils {
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
