package com.xf.oschina.base;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.xf.oschina.listener.BaseViewListener;
import com.xf.oschina.utils.Resource;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseViewModel extends ViewModel {
    public static String TAG = BaseViewModel.class.getSimpleName();
    protected LiveData<Resource<Object>> liveData;
    protected MutableLiveData<Map<String, Object>> params = new MutableLiveData<>();
    protected Map<String, Object> param = new HashMap<>();
    public BaseViewListener listener;

    public LiveData<Resource<Object>> getLiveObservableData() {
        return liveData;
    }

    public void refresh(Object... object) {
        param.clear();
        params.setValue(null);
    }

    public void loadMore(Object... object) {
        param.clear();
        params.setValue(null);
    }

    public static void reLoad() {
    }


    public void registerListener(BaseViewListener listener) {
        this.listener = listener;
    }

    public void unRegisterListener() {
        listener = null;
    }

    public abstract void onResume();
}
