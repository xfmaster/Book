package com.xf.oschina.base;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.xf.oschina.manager.DaoManager;

import org.greenrobot.greendao.AbstractDao;

import java.util.Collection;
import java.util.List;

/**
 * @author xf
 * @time 2018/6/15  11:57
 * @describe 数据库操作基类
 */
public abstract class BaseDao<T> {
    private String tempTableName = "";

    /**
     * 获取dao实例
     *
     * @return
     */
    public AbstractDao getDao(Class<T> clazz) {
        if (isExistTable(clazz)) {
            return DaoManager.getInstance().getSession().getDao(clazz);
        }
        return null;
    }

    public abstract void saveOrReplace(T t);

    public abstract void saveListOrReplace(List<T> list);

    public abstract LiveData<T> search(Class<T> tClass);

    public abstract LiveData<List<T>> searchAll(Class<T> tClass);

    public abstract LiveData<T> searchById(Class<T> tClass, String value);

    public abstract LiveData<List<T>> searchByKey(Class<T> tClass, String key);

    public abstract LiveData<List<T>> searchByKey(Class<T> tClass, String key, String value);

    public abstract void deleletAll(Class<T> tClass);

    public abstract void deleletEntity(T t);

    public abstract void deleletById(Class<T> tClass, Object id);

    public abstract LiveData<List<T>> searchPage(Class<T> tClass, int pageIndex);

    public abstract LiveData<List<T>> searchPage(Class<T> tClass, int pageIndex, Object key, Object value);

    public boolean isExistTable(Class clz) {
        try {
            String simpleName = clz.getSimpleName().toUpperCase();
            if (tempTableName.equals(simpleName)) {
                return true;
            }
            Collection<AbstractDao<?, ?>> daos = DaoManager.getInstance().getSession().getAllDaos();
            for (AbstractDao dao : daos) {
                String tablename = dao.getTablename().replace("_", "");
                if (simpleName.equals(tablename)) {
                    tempTableName = tablename;
                    return true;
                }
                Log.d("BaseDao", tablename + ">>>>" + simpleName);
            }
//            DaoManager.getInstance().getSession().getDao(clz);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
