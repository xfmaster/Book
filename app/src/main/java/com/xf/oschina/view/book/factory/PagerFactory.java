package com.xf.oschina.view.book.factory;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public abstract class PagerFactory {

    public abstract void onDraw(Canvas canvas);


    /**
     * @author xf
     * @time 2018/8/3  10:37
     * @describe 画章节
     */
    abstract void drawChapter(Canvas canvas);


    /**
     * @author xf
     * @time 2018/8/3  10:36
     * @describe 画正文部分
     */
    abstract void drawContent(Canvas canvas);

    /**
     * @author xf
     * @time 2018/8/3  10:42
     * @describe 画底部时间，电量
     */
    abstract void drawBottomText(Canvas canvas);

    /**
     * @author xf
     * @time 2018/8/3  10:42
     * @describe 背景
     */
    abstract void drawBacground(Canvas canvas);

    /**
     * @author xf
     * @time 2018/8/3  10:42
     * @describe 阴影
     */
    abstract void drawShadow(Canvas canvas);

    /**
     * @author xf
     * @time 2018/8/3  10:42
     * @describe 画翻页样式
     */
    abstract void drawStyle(Canvas canvas);

    /**
     * @author xf
     * @time 2018/8/3  10:42
     * @describe 设置文本颜色
     */
    public abstract void setTextColor(int color);

    /**
     * @author xf
     * @time 2018/8/3  10:42
     * @describe 设置文本大小
     */
    public abstract void setTextSize(float size);

    /**
     * @author xf
     * @time 2018/8/3  10:42
     * @describe 章节
     */
    public  abstract void setTextTitle(String text);

    /**
     * @author xf
     * @time 2018/8/3  10:42
     * @describe 章节
     */
    public  abstract void setTextContent(String text);

    /**
     * @author xf
     * @time 2018/8/3  10:42
     * @describe 设置文本
     */
    public    abstract void setBackgroundColor(int color);

    /**
     * @author xf
     * @time 2018/8/3  10:42
     * @describe 设置文本
     */
    public abstract void setBackgroundDrawble(Drawable drawble);

    /**
     * @author xf
     * @time 2018/8/3  10:42
     * @describe 重置
     */
    public void reset() {
    }

    public String getNextPagerText() {
        return null;
    }
}
