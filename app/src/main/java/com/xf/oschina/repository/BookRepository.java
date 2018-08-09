package com.xf.oschina.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.xf.oschina.api.ObjectApiService;
import com.xf.oschina.base.BaseDao;
import com.xf.oschina.module.book.domain.Book;
import com.xf.oschina.module.book.domain.BookData;
import com.xf.oschina.utils.AbsentLiveData;
import com.xf.oschina.utils.ApiResponse;
import com.xf.oschina.utils.AppExecutors;
import com.xf.oschina.utils.NetUtils;
import com.xf.oschina.utils.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class BookRepository {
    private AppExecutors appExecutors;
    private BaseDao dao;
    private ObjectApiService objectApiService;
    private static final String TAG = BookRepository.class.getName();

    @Inject
    BookRepository(AppExecutors appExecutors, @Named("EntityManager") BaseDao dao, ObjectApiService objectApiService) {
        this.dao = dao;
        this.objectApiService = objectApiService;
        this.appExecutors = appExecutors;
        Log.d("xf", objectApiService + "xufeng");
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
                Log.d(TAG, "shouldFetch" );
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
            case "BookData":
                liveData = objectApiService.getBooks((Map<String, String>) params);
                break;
            case "CommentData":
                String id = (String) ((Map) params).get("bookId");
                ((Map) params).remove("bookId");
                liveData = objectApiService.getBookReviews(id, (Map<String, String>) params);
                break;
        }
        return liveData;
    }


    /*
        从数据库获取数据，每添加一个网络请求都必须在此写一些逻辑
         */
    private LiveData<Object> getDataFromDb(Class clazz, Object params) {
        String name = clazz.getSimpleName();
        switch (name) {
            case "BookData": {
                try {
                    if (params instanceof HashMap) {
                        HashMap<String, Object> map = (HashMap<String, Object>) params;
                        if (map.containsKey("start")) {
                            BookData bookData = new BookData();
                            int start = (int) map.get("start");
                            bookData.setStart(start);
                            bookData.setBooks((List<Book>) dao.searchPage(Book.class, start, "tag", map.get("tag")).getValue());
                            MutableLiveData<Object> liveData = new MutableLiveData<>();
                            liveData.setValue(bookData);
                            return liveData;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return AbsentLiveData.create();
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
            case "BookData": {
                try {
                    if (data instanceof BookData) {
                        BookData bookData = (BookData) data;
                        if (params instanceof Map) {
                            String tag = (String) ((Map) params).get("tag");
                            for (Book book : bookData.getBooks()) {
                                book.setTag(tag);
                                dao.saveOrReplace(book);
                            }
                        } else
                            dao.saveListOrReplace(bookData.getBooks());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
