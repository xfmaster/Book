package com.xf.oschina;

import android.app.Activity;
import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.xf.oschina.common.CrashHandler;
import com.xf.oschina.compnent.AppInjector;
import com.xf.oschina.manager.DaoManager;
import com.xf.oschina.utils.Density;
import com.xf.oschina.utils.LogUtils;
import com.xf.oschina.utils.NetUtils;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class BookApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        CrashHandler.getInstance().init(this);
        LeakCanary.install(this);
        AppInjector.init(this);
        DaoManager.initeDao(this);
        NetUtils.inite(getApplicationContext());
        LogUtils.init(this);
        Density.setDensity(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
