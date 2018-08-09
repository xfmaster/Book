package com.xf.oschina.module.story.module;

import android.app.Activity;
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
import com.xf.oschina.module.book.domain.BookData;
import com.xf.oschina.module.story.domain.BooksBean;
import com.xf.oschina.module.story.domain.EBook;
import com.xf.oschina.module.story.ui.EBookDetailActivity;
import com.xf.oschina.repository.EBookRepository;
import com.xf.oschina.utils.AbsentLiveData;

import java.util.List;

import javax.inject.Inject;

public class StoryViewModule extends BaseViewModel implements OnItemClick {
    private int page = 0;
    private final String OPTION_IMAGE = "logo";

    @Inject
    public StoryViewModule(EBookRepository repository) {
        liveData = Transformations.switchMap(params, type -> {
            if (type == null) {
                Log.d(TAG, type + "");
                return AbsentLiveData.create();
            } else {
                Log.d(TAG, type + "");
                return repository.loadData(type, EBook.class);
            }
        });
    }

    public void refresh(Object id) {
        super.refresh(id);
        page = 0;
        param.put("id", id);
        param.put("index", page);
        params.setValue(param);
    }

    public void loadMore(Object id) {
        super.loadMore(id);
        page++;
        param.put("id", id);
        param.put("index", page * 10);
        params.setValue(param);
    }

    @Override
    public void onResume() {
        if (liveData != null && listener != null) {
            liveData.observe((LifecycleOwner) listener, result -> {
                listener.cancelNoDataView();
                List<BooksBean> booksBeans = (List<BooksBean>) result.data;
                if (booksBeans != null) {
                    if (booksBeans != null && booksBeans.size() > 0) {
                        if (page == 0) {
                            listener.refreshData(booksBeans);

                        } else {
                            listener.loadMoreData(booksBeans);
                        }
                    } else {
                        listener.showNoData();
                    }

                } else {
                    if (page > 0) {
                        listener.showToast("哎呀！到底啦！");
                        listener.loadMoreData(booksBeans);
                    } else {
                        listener.showNoData();
                    }
                }
                listener.cancelLoading();
            });
        }
    }

    @Override
    public void onItemClick(View view, Object object) {
        Intent intent = new Intent(view.getContext(), EBookDetailActivity.class);
        intent.putExtra("id", ((BooksBean) object).get_id());
        intent.putExtra("imgUrl", ((BooksBean) object).getCover());
        intent.putExtra("title", ((BooksBean) object).getTitle());
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) view.getContext(), view.findViewById(R.id.logo), OPTION_IMAGE);
        ActivityCompat.startActivity(view.getContext(), intent, options.toBundle());
    }
}
