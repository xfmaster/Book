package com.xf.oschina.view;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.scwang.smartrefresh.layout.util.DensityUtil;

public class ViewPagerTransform implements ViewPager.PageTransformer {
    @Override
    public void transformPage(@NonNull View page, float position) {
        page.setAlpha(1);
        if (position <= 0.0f) {
//被滑动的那页，设置水平位置偏移量为0，即无偏移
            page.setTranslationX(0f);
            page.setClickable(true);
        } else {//未被滑动的页
            page.setClickable(false);
            page.setTranslationX((-page.getWidth() * position));

            //缩放比例

            float scale = (page.getWidth() - 10 * position) / (float) (page.getWidth());

            page.setScaleX(scale);

            page.setScaleY(scale);

            page.setTranslationY(10 * position);

        }
    }
}
