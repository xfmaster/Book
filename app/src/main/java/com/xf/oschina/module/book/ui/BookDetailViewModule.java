package com.xf.oschina.module.book.ui;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Transformations;
import android.util.Log;

import com.xf.oschina.base.BaseViewModel;
import com.xf.oschina.module.book.domain.CommentData;
import com.xf.oschina.repository.Repository;
import com.xf.oschina.utils.AbsentLiveData;

import javax.inject.Inject;

public class BookDetailViewModule extends BaseViewModel {
    private String COMMENT_FIELDS = "id,rating,author,title,updated,comments,summary,votes,useless";
    private String SERIES_FIELDS = "id,title,subtitle,origin_title,rating,author,translator,publisher,pubdate,summary,images,pages,price,binding,isbn13,series";
    private int REVIEWS_COUNT = 5;
    private int SERIES_COUNT = 6;
    private int PAGE = 0;

    @Inject
    public BookDetailViewModule(Repository repository) {
        liveData = Transformations.switchMap(params, data -> {
            if (data == null) {
                Log.d(TAG, data + "");
                return AbsentLiveData.create();
            } else {
                Log.d(TAG, data + "");
                return repository.loadDataNoDb(data, CommentData.class);
            }
        });
    }

    @Override
    public void refresh(Object... object) {
        super.refresh(object);
        //@Path("bookId") String bookId, @Query("start") int start, @Query("count") int count, @Query("fields") String fields
        param.put("fields", COMMENT_FIELDS);
        param.put("count", 10);
        param.put("start", 0);
        param.put("bookId", object[0]);
        params.setValue(param);
    }

    @Override
    public void loadMore(Object... object) {
        super.loadMore(object);
        PAGE++;
        param.put("fields", COMMENT_FIELDS);
        param.put("count", REVIEWS_COUNT);
        param.put("start", PAGE * REVIEWS_COUNT);
        param.put("bookId", object);
        params.setValue(param);
    }

    @Override
    public void onResume() {
        liveData.observe((LifecycleOwner) listener, result -> {
            if (result != null && result.data != null) {
                Log.d("BookDetailActivity", result.data.toString());
                if (result.data != null && result.data instanceof CommentData) {
                    CommentData data = (CommentData) result.data;
                    if (data.getStart() == 0) {
                        listener.refreshData(data);
                    } else {
                        listener.loadMoreData(data);
                    }
                    listener.cancelLoading();
                } else {
                    listener.cancelLoading();
                    listener.showNoData();
                }
            }
        });
    }
}
