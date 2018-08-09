package com.xf.oschina.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.xf.oschina.api.EbookApiService;
import com.xf.oschina.api.ObjectApiService;
import com.xf.oschina.base.BaseDao;
import com.xf.oschina.module.book.domain.Book;
import com.xf.oschina.module.book.domain.BookData;
import com.xf.oschina.module.story.domain.BooksBean;
import com.xf.oschina.module.story.domain.EBook;
import com.xf.oschina.utils.AbsentLiveData;
import com.xf.oschina.utils.ApiResponse;
import com.xf.oschina.utils.AppExecutors;
import com.xf.oschina.utils.NetUtils;
import com.xf.oschina.utils.Resource;

import org.greenrobot.greendao.AbstractDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class EBookRepository {
    private AppExecutors appExecutors;
    private BaseDao dao;
    private EbookApiService ebookApiService;
    private static final String TAG = EBookRepository.class.getName();

    @Inject
    EBookRepository(AppExecutors appExecutors, @Named("EntityManager") BaseDao dao, EbookApiService ebookApiService) {
        this.dao = dao;
        this.ebookApiService = ebookApiService;
        this.appExecutors = appExecutors;
        Log.d("xf", ebookApiService + "xufeng");
    }

    /**
     * @author xf
     * @time 2018/6/20  11:28
     * @describe 获取单个数据, 此方法必须有数据库缓存
     */
    public LiveData<Resource<Object>> loadData(Object params, Class<?> tClass) {
        return new NetworkBoundResource<Object, Object>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull Object item) {
                Log.d(TAG, "saveCallResult=" + item);
                saveData(tClass, item, params);
            }

            @Override
            protected boolean shouldFetch() {
                Log.d(TAG, "shouldFetch");
                return NetUtils.isNetworkAvalible();
            }

            @Override
            protected boolean shouldSaveDb() {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<Object> loadFromDb() {
                Log.d(TAG, "loadFromDb");
                if (dao.isExistTable(tClass))
                    return dao.search(tClass);
                else {
                    return getDataFromDb(tClass, params);
                }
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Object>> createCall() {
                Log.d(TAG, "createCall");
                return (LiveData<ApiResponse<Object>>) getData(tClass, params);
            }

            @Override
            protected Object processResponse(ApiResponse<Object> response) {
                Log.d(TAG, "processResponse====" + response.body);
                return super.processResponse(response);
            }
        }.asLiveData();

    }

    /**
     * @author xf
     * @time 2018/6/20  11:28
     * @describe 获取单个数据, 此方法没有数据库缓存
     */
    public LiveData<Resource<Object>> loadDataNoDb(Object params, Class<?> tClass) {
        return new NetworkBoundResource<Object, Object>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull Object item) {
            }

            @Override
            protected boolean shouldFetch() {
                Log.d(TAG, "shouldFetch");
                return NetUtils.isNetworkAvalible();
            }

            @Override
            protected boolean shouldSaveDb() {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<Object> loadFromDb() {
                Log.d(TAG, "loadFromDb");
                return AbsentLiveData.create();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Object>> createCall() {
                Log.d(TAG, "createCall");
                return (LiveData<ApiResponse<Object>>) getData(tClass, params);
            }

            @Override
            protected Object processResponse(ApiResponse<Object> response) {
                Log.d(TAG, "processResponse====" + response.body);
                return super.processResponse(response);
            }
        }.asLiveData();

    }

    /**
     * @author xf
     * @time 2018/6/20  11:28
     * @describe 获取所有列表数据
     */
    public LiveData<Resource<List<Object>>> getListData(Object params, Class<?> tClass) {
        return new NetworkBoundResource<List<Object>, List<Object>>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull List<Object> item) {
                dao.saveListOrReplace(item);
            }

            @Override
            protected boolean shouldFetch() {
                return NetUtils.isNetworkAvalible();
            }

            @NonNull
            @Override
            protected LiveData<List<Object>> loadFromDb() {
                return dao.searchAll(tClass);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Object>>> createCall() {
                return (LiveData<ApiResponse<List<Object>>>) getData(tClass, params);
            }

            @Override
            protected boolean shouldSaveDb() {
                return dao.isExistTable(tClass);
            }

        }.asLiveData();
    }

    private LiveData<?> getData(Class<?> tClass, Object params) {
        String name = tClass.getSimpleName();
        LiveData<?> liveData = null;
        Log.d(TAG, "name:" + name);
        switch (name) {
            case "EBook":
                liveData = ebookApiService.getEbook((String) ((Map) params).get("id"));
                break;
            case "EBookDetail":
                liveData = ebookApiService.getBookDetail((String) ((Map) params).get("id"));
                break;
            case "EBookData":
                liveData = ebookApiService.getEBookComment((String) ((Map) params).get("id"), (Integer) ((Map) params).get("limit"));
                break;
            case "BookChapter":
                liveData = ebookApiService.getBookChapters((String) ((Map) params).get("id"));
                break;
            case "ChapterRead":
                liveData = ebookApiService.getChapterContent((String) ((Map) params).get("url"));
                break;
            default:
                return AbsentLiveData.create();
        }
        return liveData;
    }


    /*
        从数据库获取数据，每添加一个网络请求都必须在此写一些逻辑
         */
    private LiveData<Object> getDataFromDb(Class clazz, Object params) {
        String name = clazz.getSimpleName();
        switch (name) {
            case "EBook": {
                Map map = (Map) params;
                return dao.searchPage(BooksBean.class, (Integer) map.get("index"), "parentId", map.get("id"));
            }
        }
        return AbsentLiveData.create();
    }

    /*
    保存数据，每添加一个网络请求都必须在此写一些逻辑
     */
    private void saveData(Class clazz, Object data, Object params) {
        String name = clazz.getSimpleName();
        switch (name) {
            case "EBook": {
                try {
                    if (data instanceof EBook) {
                        EBook eBook = (EBook) data;
                        Map map = (Map) params;
                        String id = (String) map.get("id");
                        List<BooksBean> booksBeanList = eBook.getRanking().getBooks();
                        for (BooksBean book : booksBeanList) {
                            book.setParentId(id);
                            dao.saveOrReplace(book);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
