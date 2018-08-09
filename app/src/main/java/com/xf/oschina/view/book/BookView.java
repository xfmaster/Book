package com.xf.oschina.view.book;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.xf.oschina.R;
import com.xf.oschina.utils.LogUtils;
import com.xf.oschina.utils.ScreenUtils;
import com.xf.oschina.view.book.factory.CommonPageFactory;
import com.xf.oschina.view.book.factory.PagerFactory;

public class BookView extends AppCompatTextView {
    private float clickX, clickY;
    /**
     * 文本的颜色
     */
    private int mTextColor;
    /**
     * 文本的背景颜色
     */
    private int mBackgroundColor;
    /**
     * 文本的大小
     */
    private float mTextSize;

    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;
    private PagerFactory pageFactory;

    public BookView(Context context) {
        super(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setTranslationZ(DensityUtil.dp2px(5));
            setElevation(DensityUtil.dp2px(5));
        }
    }

    public BookView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setTranslationZ(DensityUtil.dp2px(5));
            setElevation(DensityUtil.dp2px(5));
        }
    }

    public BookView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        mPaint = new Paint();
//        mPaint.setTextSize(mTextSize);
//        mPaint.setColor(mTextColor);
//        //获得绘制文本的宽和高
//        mBound = new Rect();
//        mPaint.getTextBounds(mText, 0, mText.length(), mBound);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setTranslationZ(DensityUtil.dp2px(5));
            setElevation(DensityUtil.dp2px(5));
        }
    }

    public void setPageFactory(PagerFactory pageFactory) {
        this.pageFactory = pageFactory;
        pageFactory.reset();
    }

    @Override
    public void setTextSize(float size) {
        super.setTextSize(size);
        this.mTextSize = size;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        pageFactory.setBackgroundColor(mBackgroundColor);
//        pageFactory.setTextColor(mTextColor);
//        pageFactory.setTextSize(getTextSize());
//        pageFactory.setTextContent(getText().toString());
        pageFactory.onDraw(canvas);
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
        this.mTextColor = color;
    }

    @Override
    public void setBackgroundColor(int color) {
        super.setBackgroundColor(color);
        mBackgroundColor = color;
    }

}
