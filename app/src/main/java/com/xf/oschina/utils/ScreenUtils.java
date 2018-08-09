package com.xf.oschina.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class ScreenUtils {
    public static int getScreenWidth(Context mContext) {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context mContext) {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }
}
