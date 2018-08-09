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

package com.xf.oschina.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;

import com.xf.oschina.utils.ApiResponse;
import com.xf.oschina.utils.AppExecutors;
import com.xf.oschina.utils.Objects;
import com.xf.oschina.utils.Resource;


/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 * <p>
 * You can read more about it in the <a href="https://developer.android.com/arch">Architecture
 * Guide</a>.
 *
 * @param <ResultType>
 * @param <RequestType>
 */
public abstract class NetworkBoundResource<ResultType, RequestType> {
    private static final String TAG = NetworkBoundResource.class.getName();
    private final AppExecutors appExecutors;

    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    @MainThread
    public NetworkBoundResource(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        LiveData<ResultType> dbSource = new MutableLiveData<>();
        if (shouldFetch()) {
            Log.d(TAG, dbSource.getValue() + ">>shouldFetch true");
            fetchFromNetwork(dbSource);
        } else {
            Log.d(TAG, dbSource.getValue() + ">>shouldFetch false");
            result.addSource(dbSource, newData -> setValue(Resource.success(newData)));
        }
    }

    @MainThread
    private void setValue(Resource<ResultType> newValue) {
        if (!Objects.equals(result.getValue(), newValue)) {
            result.setValue(newValue);
        }
    }

    private void fetchFromNetwork(final LiveData<ResultType> dbSource) {
        LiveData<ApiResponse<RequestType>> apiResponse = createCall();
        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
        try {
            if (shouldSaveDb())
                result.addSource(dbSource, newData -> setValue(Resource.loading(newData)));
            result.addSource(apiResponse, response -> {
                result.removeSource(apiResponse);
                result.removeSource(dbSource);
                //noinspection ConstantConditions
                if (response!=null&&response.isSuccessful()) {
                    appExecutors.diskIO().execute(() -> {
                        Log.d(TAG, response.body + ">>>");
                        if (shouldSaveDb()) {
                            saveCallResult(processResponse(response));
                            appExecutors.mainThread().execute(() ->
                                    // we specially request a new live data,
                                    // otherwise we will get immediately last cached value,
                                    // which may not be updated with latest results received from network.
                                    result.addSource(loadFromDb(),
                                            newData -> setValue(Resource.success(newData)))
                            );
                        } else {
                            ApiResponse<RequestType> value = apiResponse.getValue();
                            Object obj = value.body;
                            Resource<Object> success = Resource.success(obj);
                            appExecutors.mainThread().execute(() ->
                                    result.setValue((Resource<ResultType>) success));
                        }
                    });
                } else {
                    onFetchFailed();
                    LiveData<ResultType> fromDb = loadFromDb();
                    Log.d(TAG, response.errorMessage + ">>>");
                    result.addSource(fromDb,
                            newData -> setValue(Resource.error(response.errorMessage, newData)));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            onFetchFailed();
            result.addSource(dbSource,
                    newData -> setValue(Resource.error(e.getMessage(), newData)));
        }
    }

    protected void onFetchFailed() {
    }

    public LiveData<Resource<ResultType>> asLiveData() {
        return result;
    }

    @WorkerThread
    protected RequestType processResponse(ApiResponse<RequestType> response) {
        return response.body;
    }

    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);

    @MainThread
    protected abstract boolean shouldFetch();

    @MainThread
    protected abstract boolean shouldSaveDb();

    @NonNull
    @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    @NonNull
    @MainThread
    protected abstract LiveData<ApiResponse<RequestType>> createCall();
}
