package com.xf.oschina.view.book.factory;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.xf.oschina.R;
import com.xf.oschina.utils.DensityUtils;
import com.xf.oschina.utils.LogUtils;
import com.xf.oschina.utils.ScreenUtils;
import com.xf.oschina.utils.TimeUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class CommonPageFactory extends PagerFactory {
    private Paint mPaint;
    private int textColor, backgroundColor;
    private Drawable drawable;
    private float textSize;
    private String mTextTitle, mTextContent;
    private float leftMargin, rightMargin, topMargin, bottomMargin;
    //行间距
    private int mTextInterval;
    //行间距
    private int mInterval;
    //标题的行间距
    private int mTitleInterval;
    private int screenHeight, screenWidth;
    private int contentHeight;
    private float mVisibleWidth, mVisibleHeight;
    private int wordCount;
    private int lineSpn = 0;//这里是换行符计算
    private int tempPagerCount = 0;
    private String nextPagerText = "";
    private int mShadowWidth = 80;
    private Drawable mLeftShadow;

    public CommonPageFactory(Context context) {
        rightMargin = topMargin = bottomMargin = DensityUtil.dp2px(20);
        leftMargin = DensityUtil.dp2px(15);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.LEFT);
        mTextInterval = DensityUtils.sp2px(context, textSize) + DensityUtil.dp2px(2);
        mTitleInterval = DensityUtils.sp2px(context, textSize);
        screenHeight = ScreenUtils.getScreenHeight(context);
        screenWidth = ScreenUtils.getScreenWidth(context);
        contentHeight = (int) (screenHeight - 2 * topMargin);
        mVisibleHeight = contentHeight;
        mVisibleWidth = screenWidth - 2 * rightMargin;
        mLeftShadow = context.getResources()
                .getDrawable(R.drawable.book_pager_shadow);
    }


//    public static CommonPageFactory getInstance(Context context) {
//        if (instance == null) {
//            instance = new CommonPageFactory(context);
//        }
//        return instance;
//    }

    @Override
    public void onDraw(Canvas canvas) {
        drawBacground(canvas);
        drawChapter(canvas);
        drawContent(canvas);
        drawBottomText(canvas);
        drawShadow(canvas);
    }

    @Override
    void drawChapter(Canvas canvas) {
        if (mTextTitle == null)
            return;
        mPaint.setTextSize(textSize + 4);
        mPaint.setColor(textColor);
        mPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(mTextTitle, leftMargin, topMargin, mPaint);
        contentHeight -= (topMargin + mPaint.getTextSize());
    }

    @Override
    void drawContent(Canvas canvas) {
        mPaint.setTextSize(textSize);
        mPaint.setTextAlign(Paint.Align.LEFT);
        if (mTextContent != null && mTextContent.length() > 0) {
            LogUtils.d(wordCount + ">>>>>wordCount");
            if (mTextContent.length() < wordCount) {
                canvas.drawText(mTextContent, leftMargin, mInterval + topMargin, mPaint);
                tempPagerCount += mTextContent.length();
            } else {
                drawLine(wordCount, mInterval, canvas);
            }
            contentHeight = (int) mVisibleHeight;
            if (tempPagerCount + lineSpn < mTextContent.length()) {
                nextPagerText = mTextContent.substring(tempPagerCount + lineSpn);
            } else
                nextPagerText = "";
            LogUtils.d(lineSpn + ">>>>>>lineSpn");
            lineSpn = 0;
            tempPagerCount = 0;
            LogUtils.d("mTextContent=" + nextPagerText);
        }
    }

    private void drawLine(int wordCount, float titleInterval, Canvas canvas) {
        int index = 0;
        String[] lines = mTextContent.split("\n");
        for (int n = 0; n < lines.length; n++) {
            String line = lines[n];
            int dp = line.length() % wordCount;
            int lineCount = line.length() / wordCount;//计算按一个换行符截取的字符串多少行能显示
            if (dp != 0)
                lineCount += 1;
            if (contentHeight < titleInterval + bottomMargin) {//当到底部是跳出循环，并计算剩余字符
                return;
            }
            if (lineCount == 1) {
                index++;
                contentHeight -= titleInterval;
                if (contentHeight < titleInterval + bottomMargin) {//当到底部是跳出循环，并计算剩余字符
                    return;
                }
                canvas.drawText(line, leftMargin, titleInterval * index + topMargin, mPaint);
                LogUtils.d(line);
                tempPagerCount += line.length();
            } else {
                for (int m = 0; m < lineCount; m++) {
                    if (contentHeight < titleInterval + bottomMargin) {//当到底部是跳出循环，并计算剩余字符
                        return;
                    }
                    index++;
                    contentHeight -= titleInterval;
                    if (m == lineCount - 1 && m > 0) {
                        int start = m * wordCount;
                        String s = line.substring(start, line.length());
                        tempPagerCount += s.length();
                        LogUtils.d("else if>>>" + s);
                        canvas.drawText(s, leftMargin, titleInterval * index + topMargin, mPaint);
                    } else {
                        String s = line.substring(m * wordCount, wordCount * m + wordCount);
                        tempPagerCount += s.length();
                        LogUtils.d("else else>>>" + s);
                        canvas.drawText(s, leftMargin, titleInterval * index + topMargin, mPaint);
                    }
                }
            }
            lineSpn++;
        }
    }

    @Override
    void drawBottomText(Canvas canvas) {
        mPaint.setTextSize(textSize - 4);
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(TimeUtils.getTime(), screenWidth / 2, screenHeight - DensityUtil.dp2px(10), mPaint);
    }

    @Override
    void drawBacground(Canvas canvas) {
        canvas.drawColor(backgroundColor);
    }

    @Override
    void drawShadow(Canvas canvas) {
        /* 保存画板 */
        canvas.save();
        /*设置 drawable 的大小范围*/
        mLeftShadow.setBounds(0, 0, mShadowWidth, screenHeight);
        /*让画布平移一定距离*/
        canvas.translate(screenWidth + mShadowWidth, 0);
        /*绘制Drawable*/
        mLeftShadow.draw(canvas);
        /*恢复画布的状态*/
        canvas.restore();
    }

    @Override
    void drawStyle(Canvas canvas) {

    }

    @Override
    public void setTextColor(int color) {
        textColor = color;
    }

    @Override
    public void setTextSize(float size) {
        textSize = size;
    }

    @Override
    public void setTextTitle(String text) {
        mTextTitle = text;
    }

    @Override
    public void setTextContent(String text) {
        if (text == null)
            return;
        try {
            mTextContent = new String(text.getBytes("UTF-8"), "UTF-8");
            mPaint.setTextSize(textSize);
            wordCount = mPaint.breakText(new String(" 皱眉思虑了瞬间，萧媚还是打消了过去的念头，现在的两人，已经不在同一个阶层之上，以萧炎最近几年的表现，成年后，顶多只能作为家族中的下层人员，而天赋优秀的她，则将会成为家族重点培养的强者，前途可以说是不可限量。".getBytes("UTF-8"), "utf-8"),
                    true, mVisibleWidth, null);
            mInterval = (int) (mTextInterval + mPaint.getTextSize());
            LogUtils.d(screenHeight + ">>>>>" + screenWidth + "?>>>mTitleInterval" + mTitleInterval + ">>>mTextInterval" + mTextInterval + ">>>>>textSize" + textSize);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reset() {
    }

    @Override
    public void setBackgroundColor(int color) {
        backgroundColor = color;
    }

    @Override
    public void setBackgroundDrawble(Drawable drawable) {
        this.drawable = drawable;
    }

    @Override
    public String getNextPagerText() {
        return nextPagerText;
    }
}
