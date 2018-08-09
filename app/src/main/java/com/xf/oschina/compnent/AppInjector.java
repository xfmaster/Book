/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xf.oschina.compnent;

import com.xf.oschina.BookApplication;
import com.xf.oschina.base.Injectable;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;

/**
 * Helper class to automatically inject fragments if they implement {@link Injectable}.
 */
public class AppInjector {
    private AppInjector() {
    }

    public static void init(BookApplication bookApplication) {
        DaggerAppComponent.builder().application(bookApplication)
                .build().inject(bookApplication);
        bookApplication
                .registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                    @Override
                    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                        handleActivity(activity);
                        Log.d("AppInjector", activity + "onActivityCreated");
                    }

                    @Override
                    public void onActivityStarted(Activity activity) {
                        Log.d("AppInjector", activity + "onActivityStarted");
                    }

                    @Override
                    public void onActivityResumed(Activity activity) {
                        Log.d("AppInjector", activity + "onActivityResumed");
                    }

                    @Override
                    public void onActivityPaused(Activity activity) {
                        Log.d("AppInjector", activity + "onActivityPaused");
                    }

                    @Override
                    public void onActivityStopped(Activity activity) {
                        Log.d("AppInjector", activity + "onActivityStopped");
                    }

                    @Override
                    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                        Log.d("AppInjector", activity + "onActivitySaveInstanceState");
                    }

                    @Override
                    public void onActivityDestroyed(Activity activity) {
                        Log.d("AppInjector", activity + "onActivityDestroyed");
                    }
                });
    }

    private static void handleActivity(Activity activity) {
        AndroidInjection.inject(activity);
        if (activity instanceof FragmentActivity) {
            ((FragmentActivity) activity).getSupportFragmentManager()
                    .registerFragmentLifecycleCallbacks(
                            new FragmentManager.FragmentLifecycleCallbacks() {
                                @Override
                                public void onFragmentCreated(FragmentManager fm, Fragment f,
                                                              Bundle savedInstanceState) {
                                    if (f instanceof Injectable) {
                                        AndroidSupportInjection.inject(f);
                                    }
                                }
                            }, true);
        }
    }
}
