package com.xf.oschina.manager;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.util.Log;

import com.xf.oschina.base.BaseDao;
import com.xf.oschina.dao.BooksBeanDao;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;

import java.util.List;

public class EntityManager<T> extends BaseDao<T> {
    private static String TAG = EntityManager.class.getName();


    /**
     * @author xf
     * @time 2018/6/14  9:19
     * @describe 保存或者替换数据
     */
    public void saveOrReplace(T t) {
        try {
            AbstractDao dao = getDao((Class<T>) t.getClass());
            if (dao == null)
                return;
            dao.saveInTx();
            dao.insertOrReplace(t);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, e.getMessage());
        }
    }

    /**
     * @author xf
     * @time 2018/6/14  9:56
     * @describe 保存一个集合
     */
    public void saveListOrReplace(List<T> list) {
        try {

            if (list != null && list.size() > 0) {
                AbstractDao dao = getDao((Class<T>) list.get(0).getClass());
                if (dao == null)
                    return;
                for (T t : list)
                    dao.insertOrReplace(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, e.getMessage());
        }
    }

    /**
     * @author xf
     * @time 2018/6/14  9:18
     * @describe 查询一个对象数据
     */
    public LiveData<T> search(Class<T> tClass) {
        MediatorLiveData<T> result = new MediatorLiveData<>();
        try {
            AbstractDao dao = getDao(tClass);
            if (dao == null)
                return result;
            List<T> list = dao.loadAll();
            Object obj = null;
            if (list != null && list.size() > 0) {
                T t = list.get(0);
                result.setValue(t);
            } else
                result.setValue(null);
            Log.d(TAG, obj + "");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, e.getMessage());
            result.setValue(null);
            return result;
        }
    }

    /**
     * @author xf
     * @time 2018/6/14  9:21
     * @describe 查询所有数据
     */
    public LiveData<List<T>> searchAll(Class<T> tClass) {
        MediatorLiveData<List<T>> result = new MediatorLiveData<>();
        try {
            AbstractDao dao = getDao(tClass);
            if (dao == null)
                return result;
            List list = dao.loadAll();
            Log.d(TAG, list.size() + "<<<<<<<<<<");
            result.setValue(dao.loadAll());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, e.getMessage());
            result.setValue(null);
            return result;
        }
    }

    /**
     * @author xf
     * @time 2018/6/14  9:21
     * @describe 查询所有数据
     */
    public LiveData<List<T>> searchPage(Class<T> tClass, int pageIndex) {
        MediatorLiveData<List<T>> result = new MediatorLiveData<>();
        try {
            AbstractDao dao = getDao(tClass);
            if (dao == null)
                return result;
            result.setValue(dao.queryBuilder().offset(pageIndex).limit(10).list());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, e.getMessage());
            result.setValue(null);
            return result;
        }
    }

    /**
     * @author xf
     * @time 2018/6/14  9:21
     * @describe 查询所有数据
     */
    public LiveData<List<T>> searchPage(Class<T> tClass, int pageIndex, Object key, Object value) {
        MediatorLiveData<List<T>> result = new MediatorLiveData<>();
        try {
            AbstractDao dao = getDao(tClass);
            if (dao == null)
                return result;
            Property[] properties = dao.getProperties();
            int index = -1;
            for (int i = 0; i < properties.length; i++) {
                if (key.equals(properties[i].name)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                result.setValue(dao.queryBuilder().where(dao.getProperties()[index].eq(value)).orderAsc(properties[0]).offset(pageIndex).limit(10).list());
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, e.getMessage());
            result.setValue(null);
            return result;
        }
    }

    /**
     * @author xf
     * @time 2018/6/14  9:21
     * @describe 查询所有数据
     */
    public LiveData<T> searchById(Class<T> tClass, String value) {
        T t = null;
        MediatorLiveData<T> result = new MediatorLiveData<>();
        try {
            AbstractDao dao = getDao(tClass);
            if (dao == null)
                return result;
            t = (T) dao.queryBuilder().where(dao.getProperties()[0].eq(value)).uniqueOrThrow();
            result.setValue(t);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, e.getMessage());
            result.setValue(null);
        }
        return result;
    }

    /**
     * @author xf
     * @time 2018/6/14  9:21
     * @describe 根据键查询所有数据
     */
    public LiveData<List<T>> searchByKey(Class<T> tClass, String key) {
        List<T> list = null;
        MediatorLiveData<List<T>> result = new MediatorLiveData<>();
        try {
            AbstractDao dao = getDao(tClass);
            if (dao == null)
                return result;
            Property[] properties = dao.getProperties();
            int index = -1;
            for (int i = 0; i < properties.length; i++) {
                if (key.equals(properties[i].name)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                list = dao.queryBuilder().where(dao.getProperties()[index].isNotNull()).list();
                if (list != null) {
                    result.setValue(list);
                } else {
                    result.setValue(null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, e.getMessage());
        }

        return result;
    }

    /**
     * @author xf
     * @time 2018/6/14  9:21
     * @describe 根据键值对查询所有数据
     */
    public LiveData<List<T>> searchByKey(Class<T> tClass, String key, String value) {
        List<T> list = null;
        MediatorLiveData<List<T>> result = new MediatorLiveData<>();
        try {
            AbstractDao dao = getDao(tClass);
            if (dao == null)
                return result;
            Property[] properties = dao.getProperties();
            int index = -1;
            for (int i = 0; i < properties.length; i++) {
                if (key.equals(properties[i].name)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                list = dao.queryBuilder().where(dao.getProperties()[index].eq(value)).list();
                if (list != null) {
                    result.setValue(list);
                } else {
                    result.setValue(null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, e.getMessage());
        }

        return result;
    }

    /**
     * @author xf
     * @time 2018/6/14  10:53
     * @describe 删除所数据
     */
    public void deleletAll(Class<T> tClass) {
        AbstractDao dao = getDao(tClass);
        if (dao == null)
            return;
        dao.deleteAll();
    }

    /**
     * @author xf
     * @time 2018/6/14  10:53
     * @describe 删除一条数据
     */
    public void deleletEntity(T t) {
        AbstractDao dao = getDao((Class<T>) t.getClass());
        if (dao == null)
            return;
        dao.delete(t);
    }

    /**
     * @author xf
     * @time 2018/6/14  10:53
     * @describe 删除一条数据
     */
    public void deleletById(Class<T> tClass, Object id) {
        AbstractDao dao = getDao(tClass);
        if (dao == null)
            return;
        dao.deleteByKey(id);
    }

}
