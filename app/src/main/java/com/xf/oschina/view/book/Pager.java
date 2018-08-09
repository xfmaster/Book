package com.xf.oschina.view.book;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.xf.oschina.R;
import com.xf.oschina.utils.LogUtils;
import com.xf.oschina.view.book.factory.CommonPageFactory;
import com.xf.oschina.view.book.factory.PagerFactory;
import com.xf.oschina.view.book.listener.PageTurningListener;

public class Pager extends FrameLayout {
    private int textSize;
    private int textColor;
    private int bgColor;
    private String text;
    private BookView mCurrentPager, mNextPager;
    private PagerFactory mCurrentPagerFactory, mNextPagerFactory;
    private ViewDragHelper dragHelper;
    private static final int MIN_SCOLLX = 200;
    private static final int LEFT_TOUCH = 100;
    private static final int RIGHT_TOUCH = 200;
    private static final int BOTTOM_TOUCH = 100;
    private static final int TOP_TOUCH = 100;
    private float touchX, touchY;
    private boolean isNext = false;
    private int mDragOriLeft;
    private boolean isInite = false;
    private PageTurningListener listener;

    public Pager(@NonNull Context context) {
        super(context);
    }

    public Pager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.pager);
        textSize = a.getDimensionPixelSize(R.styleable.pager_textSize, 18);
        textColor = a.getColor(R.styleable.pager_textColor, Color.BLACK);
        bgColor = a.getColor(R.styleable.pager_bgColor, Color.WHITE);
        text = a.getString(R.styleable.pager_text);
        a.recycle();
        initeData();
        attachPagerView();
    }

    public Pager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.pager);
        textSize = a.getDimensionPixelSize(R.styleable.pager_textColor, 18);
        textColor = a.getColor(R.styleable.pager_textColor, Color.BLACK);
        bgColor = a.getColor(R.styleable.pager_bgColor, Color.WHITE);
        text = a.getString(R.styleable.pager_text);
        a.recycle();
        initeData();
        attachPagerView();
    }

    private void initeData() {
        mCurrentPagerFactory = new CommonPageFactory(getContext());
        mCurrentPagerFactory.setTextSize(textSize);
        mCurrentPagerFactory.setTextColor(textColor);
        mCurrentPagerFactory.setBackgroundColor(bgColor);
        mNextPagerFactory = new CommonPageFactory(getContext());
        mNextPagerFactory.setTextSize(textSize);
        mNextPagerFactory.setTextColor(textColor);
        mNextPagerFactory.setBackgroundColor(bgColor);
    }

    private void attachPagerView() {
        mCurrentPager = new BookView(getContext());
        mCurrentPager.setPageFactory(mCurrentPagerFactory);
        mNextPager = new BookView(getContext());
        mNextPager.setPageFactory(mNextPagerFactory);
        mNextPager.setBackgroundDrawable(getResources().getDrawable(R.drawable.book_pager_shadow));
        mCurrentPager.setBackgroundDrawable(getResources().getDrawable(R.drawable.book_pager_shadow));
        addView(mNextPager, 0);
        addView(mCurrentPager, 1);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        dragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(@NonNull View child, int pointerId) {
                return true;
            }

            @Override
            public void onViewCaptured(@NonNull View capturedChild, int activePointerId) {
                super.onViewCaptured(capturedChild, activePointerId);
                mDragOriLeft = capturedChild.getLeft();
                if (!isInite) {//这里第一次进来的时候出现第二页没数据,所以加个判断重新给数据
                    String nextPagerText = mCurrentPagerFactory.getNextPagerText();
                    LogUtils.d(nextPagerText);
                    mNextPagerFactory.setTextContent(nextPagerText);
                    mNextPager.postInvalidate();
                    isInite = true;
                }
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return 0;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                LogUtils.d(dx);
                if (left > 0) {
                    return 0;
                }
                return left;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                LogUtils.d("xvel=" + xvel + ">>>isNext=" + isNext);
                if (xvel < -MIN_SCOLLX || isNext) {
                    dragHelper.settleCapturedViewAt(-getMeasuredWidth(), 0);
                    invalidate();
                    isNext = true;
                } else {
                    dragHelper.settleCapturedViewAt(mDragOriLeft, releasedChild.getTop());
                    invalidate();
                }
            }

            @Override
            public void onViewDragStateChanged(int state) {
                super.onViewDragStateChanged(state);
                LogUtils.d(state + ">>>>>>");
                if (state == ViewDragHelper.STATE_IDLE && isNext) {
                    View view = getChildAt(1);
                    if (view != null) {
                        removeView(view);
                        if (view == mNextPager) {
                            if (TextUtils.isEmpty(mCurrentPagerFactory.getNextPagerText())) {//加载更多
                                if (listener != null)
                                    listener.onNext();
//                                mNextPagerFactory.setTextContent(text);
                            } else {
                                mNextPagerFactory.setTextContent(mCurrentPagerFactory.getNextPagerText());
                            }
                            addView(mNextPager, 0);
                        } else if (view == mCurrentPager) {
                            if (TextUtils.isEmpty(mNextPagerFactory.getNextPagerText())) {//加载更多
                                if (listener != null)
                                    listener.onNext();
                            } else {
                                mCurrentPagerFactory.setTextContent(mNextPagerFactory.getNextPagerText());
                            }
                            addView(mCurrentPager, 0);
                        }
                    }
                }
            }
        });
        dragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_RIGHT);
    }


    @Override
    public void computeScroll() {
        if (dragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isNext = false;
                touchX = event.getX();
                touchY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                if (touchX > getMeasuredWidth() - RIGHT_TOUCH) {
                    if (!isInite) {
                        String nextPagerText = mCurrentPagerFactory.getNextPagerText();
                        LogUtils.d(nextPagerText);
                        mNextPagerFactory.setTextContent(nextPagerText);
                        mNextPager.postInvalidate();
                        isInite = true;
                    }
                    isNext = true;
                }
                break;
        }
        dragHelper.processTouchEvent(event);
        return true;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;

    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        mCurrentPagerFactory.setTextContent(text);
        mCurrentPager.invalidate();
    }

    public void setPageTurningListener(PageTurningListener listener) {
        this.listener = listener;
    }
}

