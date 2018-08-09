package com.xf.oschina.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Log;

import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.xf.oschina.module.story.domain.ChapterPager;
import com.xf.oschina.module.story.domain.ChapterRead;

import java.util.ArrayList;
import java.util.List;

public class ReadUtils {
    private static int screenWidth, screenHeight;
    private static int mLineCount = 50, mLineWordCount = 20;
    private static Paint mPaint = new Paint();
    private static int mVisibleWidth;
    private static int mVisibleHeight;
    /**
     * 绘制时控制文本绘制的范围
     */
    private static Rect mBound;

    public static void setBookTextConfig(Context mContext) {
        screenWidth = ScreenUtils.getScreenWidth(mContext);
        screenHeight = ScreenUtils.getScreenHeight(mContext);
        mVisibleWidth = screenWidth - DensityUtil.dp2px(40);
        mVisibleHeight = screenHeight + DensityUtils.getStatusBarHeight(mContext) - DensityUtil.dp2px(40);
        int mFontSizePx = DensityUtils.sp2px(mContext, 16);
        mLineWordCount = mVisibleWidth / (mFontSizePx);
        mPaint.setTextSize(16);
        mLineCount = mVisibleHeight / 42; // 可显示的行数

    }

    public static void setTextLineHeight(int lineHeight) {
        mLineCount = mVisibleHeight / lineHeight; // 可显示的行数
    }

    public static List<ChapterPager> getPagers(ChapterRead.Chapter chapter) {
        String body = chapter.getBody();
//        //获得绘制文本的宽和高
        List<ChapterPager> pagers = new ArrayList<>();
//        int wordCount = mPaint.breakText(body,
//                true, mVisibleWidth, null);
//        int page = body.length() / (mLineCount * wordCount);
//        int end = body.length() % (wordCount * mLineCount);
//        LogUtils.d(wordCount + ">>>>>>>>>>>>");
//        if (end != 0)
//            page++;
//        if (page != 0) {
//            for (int i = 0; i < page; i++) {
//                if (i == page - 1) {
//                    String str = chapter.getBody().substring(i * mLineCount * wordCount, mLineCount * wordCount * i + end);
//                    if (!TextUtils.isEmpty(str)) {
//                        ChapterPager pager = new ChapterPager(chapter.getChapterId(), 0, str);
//                        pagers.add(pager);
//                    }
//                } else {
//                    ChapterPager pager = new ChapterPager(chapter.getChapterId(), 0, chapter.getBody().substring(i * mLineCount * wordCount, mLineCount * wordCount * (i + 1)));
//                    pagers.add(pager);
//                }
//            }
//        } else {
            ChapterPager pager = new ChapterPager(chapter.getChapterId(), 0, chapter.getBody());
            pager.setBody(body);
            pagers.add(pager);
//        }
        LogUtils.d(body);
        return pagers;
    }
}
