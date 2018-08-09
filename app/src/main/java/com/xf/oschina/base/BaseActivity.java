package com.xf.oschina.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xf.oschina.R;
import com.xf.oschina.listener.BaseViewListener;
import com.xf.oschina.module.book.domain.Book;
import com.xf.oschina.utils.Density;
import com.xf.oschina.utils.ResourceHelper;
import com.xf.oschina.view.LoadingView;

import javax.inject.Inject;
import javax.inject.Named;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

public abstract class BaseActivity extends AppCompatActivity implements SwipeBackActivityBase, BaseViewListener {
    private SwipeBackActivityHelper mHelper;
    @Inject
    @Named("EntityManager")
    public BaseDao entityManager;
    private LoadingView loading;
    private View noDataView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Density.setDefault(this);
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
//        ResourceHelper.updateConfig(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }


    /**
     * @author xf
     * @time 2018/7/9  15:05
     * @describe 加载动画
     */
    public void showLoading() {
        try {
            loading = new LoadingView(getApplicationContext());
            View view = getSwipeBackLayout();
            ((ViewGroup) view.getRootView()).addView(loading);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author xf
     * @time 2018/7/9  15:05
     * @describe 关闭加载动画
     */
    public void cancelLoading() {
        if (loading != null)
            ((ViewGroup) getSwipeBackLayout().getRootView()).removeView(loading);
    }

    public void showToast(String message) {
        Snackbar snackbar = Snackbar.make(getSwipeBackLayout(), message, Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.common_color));
        snackbar.show();
    }

    /**
     * @author xf
     * @time 2018/7/9  15:05
     * @describe 没有数据是显示
     */
    public void showNoData() {
        View view = getSwipeBackLayout();
        if (noDataView == null)
            noDataView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.empty_or_error_layout, null);
        ((ViewGroup) view.getRootView()).addView(noDataView);
        noDataView.setOnClickListener(v -> {
            retry();
        });

    }

    /**
     * @author xf
     * @time 2018/7/9  16:45
     * @describe 隐藏没有数据显示
     */
    public void cancelNoDataView() {
        if (noDataView != null) {
            noDataView.setVisibility(View.GONE);
            ((ViewGroup) getSwipeBackLayout().getRootView()).removeView(noDataView);
        }
    }

    public abstract void retry();

    @Override
    public void loadMoreData(Object data) {

    }

    @Override
    public void refreshData(Object data) {

    }
}
