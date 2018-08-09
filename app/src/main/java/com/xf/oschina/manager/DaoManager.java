package com.xf.oschina.manager;

import android.content.Context;

import com.xf.oschina.dao.DaoMaster;
import com.xf.oschina.dao.DaoSession;

/**
 * greenDao管理类
 */
public class DaoManager {
    private static DaoManager mInstance;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    public static void initeDao(Context mContext) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(mContext, "oschina-db", null);
        mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    private DaoManager() {

    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public static DaoManager getInstance() {
        if (mInstance == null) {
            mInstance = new DaoManager();
        }
        return mInstance;
    }
}  