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

package com.xf.oschina.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.xf.oschina.base.BaseViewModel;
import com.xf.oschina.listener.BaseViewListener;

/**
 * A value holder that automatically clears the reference if the Fragment's view is destroyed.
 *
 * @param <T>
 */
public class AutoClearedValue<T> {
    private T value;
    private BaseViewModel viewModel;

    public AutoClearedValue(Fragment fragment, T value, BaseViewModel viewModel) {
        FragmentManager fragmentManager = fragment.getFragmentManager();
        fragmentManager.registerFragmentLifecycleCallbacks(
                new FragmentManager.FragmentLifecycleCallbacks() {

                    @Override
                    public void onFragmentResumed(FragmentManager fm, Fragment f) {
                        super.onFragmentResumed(fm, f);
                        if (f == fragment) {
                            if (fragment instanceof BaseViewListener && viewModel != null && viewModel.listener == null) {
                                AutoClearedValue.this.viewModel.registerListener((BaseViewListener) fragment);
                                viewModel.onResume();
                                Log.d("AutoClearedValue", " viewModel.onResume()");
                            }
                        }
                    }

                    @Override
                    public void onFragmentStarted(FragmentManager fm, Fragment f) {
                        super.onFragmentStarted(fm, f);
                        Log.d("AutoClearedValue", "onFragmentStarted");
                    }

                    @Override
                    public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
                        if (f == fragment) {
                            AutoClearedValue.this.value = null;
                            fragmentManager.unregisterFragmentLifecycleCallbacks(this);
                            if (viewModel != null) {
                                AutoClearedValue.this.viewModel.unRegisterListener();
                                AutoClearedValue.this.viewModel = null;
                            }
                        }
                    }
                }, false);
        this.value = value;
        this.viewModel = viewModel;
    }

    public AutoClearedValue(AppCompatActivity appCompatActivity, T value, BaseViewModel viewModel) {
        appCompatActivity.getApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                if (activity instanceof BaseViewListener && viewModel != null && viewModel.listener == null) {
                    AutoClearedValue.this.viewModel.registerListener((BaseViewListener) activity);
                    viewModel.onResume();
                    Log.d("AutoClearedValue", " viewModel.onResume()");
                }
                Log.d("AutoClearedValue", activity + " onActivityCreated");
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.d("AutoClearedValue", activity + " onActivityStarted");
                if (activity instanceof BaseViewListener && viewModel != null && viewModel.listener == null) {
                    AutoClearedValue.this.viewModel.registerListener((BaseViewListener) activity);
                    viewModel.onResume();
                    Log.d("AutoClearedValue", " viewModel.onResume()");
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.d("AutoClearedValue", activity + " onActivityResumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.d("AutoClearedValue", activity + " onActivityPaused");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.d("AutoClearedValue", activity + " onActivityStopped");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Log.d("AutoClearedValue", activity + " onActivitySaveInstanceState");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if (viewModel != null&&activity==appCompatActivity) {
                    AutoClearedValue.this.value = null;
                    activity.getApplication().unregisterActivityLifecycleCallbacks(this);
                    AutoClearedValue.this.viewModel.unRegisterListener();
                    AutoClearedValue.this.viewModel = null;
                }
                Log.d("AutoClearedValue", activity + " onActivityDestroyed");
            }
        });
        this.value = value;
        this.viewModel = viewModel;
    }

    public T get() {
        return value;
    }
}
