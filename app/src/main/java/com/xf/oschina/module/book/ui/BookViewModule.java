package com.xf.oschina.module.book.ui;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Transformations;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.xf.oschina.R;
import com.xf.oschina.base.BaseViewModel;
import com.xf.oschina.listener.OnItemClick;
import com.xf.oschina.module.book.activity.BookDetailActivity;
import com.xf.oschina.module.book.domain.Book;
import com.xf.oschina.module.book.domain.BookData;
import com.xf.oschina.repository.Repository;
import com.xf.oschina.utils.AbsentLiveData;

import javax.annotation.Nullable;
import javax.inject.Inject;

public class BookViewModule extends BaseViewModel implements OnItemClick {
    private static final String fields = "id,title,subtitle,origin_title,rating,author,translator,publisher,pubdate,summary,images,pages,price,binding,isbn13,series,alt,image";
    private int count = 10;
    private int page = 0;
    private static final String OPTION_IMAGE = "logo";

    @Inject
    public BookViewModule(Repository repository) {
        liveData = Transformations.switchMap(params, type -> {
            if (type == null) {
                Log.d(TAG, type + "");
                return AbsentLiveData.create();
            } else {
                Log.d(TAG, type + "");
                return repository.loadData(type, BookData.class);
            }
        });
        Log.d(TAG, "BookViewModule");
    }

    public void refresh(Object tag) {
        super.refresh(tag);
        param.put("q", "");
        param.put("tag", tag);
        param.put("start", 0);
        param.put("count", 10);
        param.put("fields", fields);
        params.setValue(param);
    }

    public void loadMore(Object tag) {
        super.loadMore(tag);
        page++;
        param.put("q", "");
        param.put("tag", tag);
        param.put("start", page * count);
        param.put("count", count);
        param.put("fields", fields);
        params.setValue(param);
    }

    @Override
    public void onItemClick(@Nullable View view, @Nullable Object object) {
        if (object instanceof Book) {
            Book book = (Book) object;
            startOptionsActivity((AppCompatActivity) view.getContext(), view.findViewById(R.id.logo), book);
        }
    }


    public static void startOptionsActivity(AppCompatActivity activity, View transitionView, Book book) {
        Intent intent = new Intent(activity, BookDetailActivity.class);
        // 这里指定了共享的视图元素
        intent.putExtra("url", book.getImages().getLarge());
        intent.putExtra("id", book.getId());
        intent.putExtra("title", book.getTitle());
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionView, OPTION_IMAGE);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }


    @Override
    public void onResume() {
        if (liveData != null && listener != null) {
            liveData.observe((LifecycleOwner) listener, result -> {
                listener.cancelNoDataView();
                BookData bookData = (BookData) result.data;
                if (bookData.getBooks().size() > 0) {
                    if (bookData.getStart() == 0) {
                        listener.refreshData(bookData);
                    } else {
                        listener.loadMoreData(bookData);
                    }
                } else {
                    if (bookData.getStart() > 0) {
                        listener.showToast("哎呀！到底啦！");
                        listener.loadMoreData(bookData);
                    } else {
                        listener.showNoData();
                    }
                }
                listener.cancelLoading();
            });
        }
    }
}
