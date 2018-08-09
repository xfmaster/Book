package com.xf.oschina.module.story.module;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.util.Log;

import com.xf.oschina.base.BaseViewModel;
import com.xf.oschina.module.book.domain.BookData;
import com.xf.oschina.module.story.domain.EBookData;
import com.xf.oschina.module.story.domain.EBookDetail;
import com.xf.oschina.repository.EBookRepository;
import com.xf.oschina.utils.AbsentLiveData;
import com.xf.oschina.utils.Resource;

import javax.inject.Inject;

public class EbookViewModule extends BaseViewModel {
    protected LiveData<Resource<Object>> comment;
    @Inject
    public EbookViewModule(EBookRepository eBookRepository) {
        liveData = Transformations.switchMap(params, data -> {
            if (data == null) {
                Log.d(TAG, data + "");
                return AbsentLiveData.create();
            } else {
                Log.d(TAG, data + "");
                return eBookRepository.loadDataNoDb(data, EBookDetail.class);
            }
        });
        comment = Transformations.switchMap(params, data -> {
            if (data == null) {
                Log.d(TAG, data + "");
                return AbsentLiveData.create();
            } else {
                Log.d(TAG, data + "");
                return eBookRepository.loadDataNoDb(data, EBookData.class);
            }
        });
    }

    @Override
    public void onResume() {
        if (liveData != null && listener != null) {
            liveData.observe((LifecycleOwner) listener, result -> {
                listener.cancelNoDataView();
                if (result.data != null) {
                    if (result.data instanceof EBookDetail) {
                        listener.refreshData(result.data);
                    } else if (result.data instanceof EBookData) {
                        listener.refreshData(((EBookData) result.data).getReviews());
                    }
                } else {
                    listener.showNoData();
                }
                listener.cancelLoading();
            });
        }
        if (comment != null && listener != null) {
            comment.observe((LifecycleOwner) listener, result -> {
                listener.cancelNoDataView();
                if (result.data != null) {
                    if (result.data instanceof EBookDetail) {
                        listener.refreshData(result.data);
                    } else if (result.data instanceof EBookData) {
                        listener.refreshData(((EBookData) result.data).getReviews());
                    }
                } else {
                    listener.showNoData();
                }
                listener.cancelLoading();
            });
        }
    }

    @Override
    public void refresh(Object... data) {
        super.refresh(data);
        param.put("id", data[0]);
        params.setValue(param);

    }

    /**
     * @author xf
     * @time 2018/7/19  15:16
     * @describe 加载评论
     */
    public void loadComment(int limit, String id) {
        param.clear();
        params.setValue(null);
        param.put("id", id);
        param.put("limit", limit);
        params.setValue(param);
    }

    @Override
    public void loadMore(Object... data) {
        super.loadMore(data);

    }
}
