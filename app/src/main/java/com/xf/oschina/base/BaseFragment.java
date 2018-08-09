package com.xf.oschina.base;


import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xf.oschina.R;
import com.xf.oschina.listener.BaseViewListener;
import com.xf.oschina.view.LoadingView;

public abstract class BaseFragment extends Fragment implements Injectable, BaseViewListener {
    private LoadingView loading;
    private View noDataView;


    /**
     * @author xf
     * @time 2018/7/9  15:05
     * @describe 加载动画
     */
    public void showLoading() {
        try {
            View view = getView();
            if (loading == null) {
                loading = new LoadingView(getActivity().getApplicationContext());
            } else {
                ((ViewGroup) view).removeView(loading);
            }
            ((ViewGroup) view).addView(loading);
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
        if (loading != null) {
            loading.setVisibility(View.GONE);
            ((ViewGroup) getView()).removeView(loading);
        }
    }

    /**
     * @author xf
     * @time 2018/7/9  15:05
     * @describe 没有数据是显示, 这里最外层必须framlayout
     */
    public void showNoData() {
        View view = getView();
        if (noDataView == null)
            noDataView = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.empty_or_error_layout, null);
        ((ViewGroup) view).addView(noDataView);
        noDataView.setOnClickListener(v -> {
            this.retry();
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
            ((ViewGroup) getView()).removeView(noDataView);
        }
    }


    public void showToast(String message) {
        Snackbar snackbar = Snackbar.make(this.getView(), message, Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.common_color));
        snackbar.show();
    }

    @Override
    public void refreshData(Object data) {

    }

    @Override
    public void loadMoreData(Object data) {

    }

    public abstract void retry();

    public void refresh() {
    }
}
