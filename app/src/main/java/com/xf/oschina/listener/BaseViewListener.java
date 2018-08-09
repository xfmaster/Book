package com.xf.oschina.listener;

public interface BaseViewListener {
    /**
     * @author xf
     * @time 2018/7/11  9:49
     * @describe 显示loading效果
     */
    void showLoading();

    /**
     * @author xf
     * @time 2018/7/11  9:49
     * @describe 关闭loading效果
     */
    void cancelLoading();

    /**
     * @author xf
     * @time 2018/7/11  9:48
     * @describe 显示没有数据
     */
    void showNoData();

    /**
     * @author xf
     * @time 2018/7/11  9:48
     * @describe 关闭没有数据显示
     */
    void cancelNoDataView();

    /**
     * @author xf
     * @time 2018/7/11  9:48
     * @describe 更新数据
     */
    void refreshData(Object data);

    void loadMoreData(Object data);

    void showToast(String msg);
}
