package com.xf.oschina.module.story.module;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.util.Log;

import com.xf.oschina.base.BaseViewModel;
import com.xf.oschina.module.story.domain.BookChapter;
import com.xf.oschina.module.story.domain.ChapterRead;
import com.xf.oschina.repository.EBookRepository;
import com.xf.oschina.utils.AbsentLiveData;
import com.xf.oschina.utils.Resource;

import java.util.List;

import javax.inject.Inject;

public class EBookReadViewModule extends BaseViewModel {
    private LiveData<Resource<Object>> charpter;
    private int charpterNumber = 0;
    List<BookChapter.MixToc.Chapters> chapters;

    @Inject
    public EBookReadViewModule(EBookRepository repository) {
        liveData = Transformations.switchMap(params, data -> {
            if (data == null)
                return AbsentLiveData.create();
            else
                return repository.loadDataNoDb(data, BookChapter.class);
        });
        charpter = Transformations.switchMap(params, data -> {
            if (data == null)
                return AbsentLiveData.create();
            else
                return repository.loadDataNoDb(data, ChapterRead.class);
        });
    }

    @Override
    public void refresh(Object... object) {
        super.refresh(object);
        param.put("id", object[0]);
        params.setValue(param);
    }

    /**
     * @author xf
     * @time 2018/7/23  15:12
     * @describe 加载章节
     */
    public void loadChapter(String url) {
        param.clear();
        params.setValue(null);
        param.put("url", url);
        params.setValue(param);
    }

    @Override
    public void loadMore(Object... object) {
        super.loadMore(object);
        charpterNumber++;
        if (charpterNumber < chapters.size()) {
            String link = chapters.get(charpterNumber).getLink();
            param.put("url", link);
            params.setValue(param);
        }
    }

    @Override
    public void onResume() {
        if (liveData != null && listener != null) {
            liveData.observe((LifecycleOwner) listener, result -> {
                if (result != null && result.data instanceof BookChapter) {
                    Log.d(TAG, result.data + "");
                    listener.refreshData(((BookChapter) result.data).getMixToc().getChapters());
                    chapters = ((BookChapter) result.data).getMixToc().getChapters();
                }
            });
        }
        if (charpter != null && listener != null) {
            charpter.observe((LifecycleOwner) listener, result -> {
                if (result != null && result.data instanceof ChapterRead) {
                    Log.d(TAG, result.data + "");
                    listener.refreshData(result.data);
                }
            });
        }
    }
}
