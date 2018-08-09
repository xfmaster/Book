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

import android.os.Build;
import android.util.Log;

import com.xf.oschina.api.EbookApiService;
import com.xf.oschina.api.ObjectApiService;
import com.xf.oschina.base.BaseDao;
import com.xf.oschina.dagger.ViewModelModule;
import com.xf.oschina.manager.EntityManager;
import com.xf.oschina.utils.HttpsUtils;
import com.xf.oschina.utils.LiveDataCallAdapterFactory;
import com.xf.oschina.utils.Tls12SocketFactory;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
class AppModule {
    @Singleton
    @Provides
    ObjectApiService provideOsChinaApiService() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder().
                connectTimeout(60, TimeUnit.SECONDS).
                readTimeout(60, TimeUnit.SECONDS)
               /* .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                }).writeTimeout(60, TimeUnit.SECONDS)*/;
//        if (AppParams.isBypassAuthen) {
//            HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
//            builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
//        } else {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT_WATCH) {
            SSLContext sslContext = null;
            try {
                sslContext = SSLContext.getInstance("TLS");
                try {
                    sslContext.init(null, null, null);
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            SSLSocketFactory socketFactory = new Tls12SocketFactory(sslContext.getSocketFactory());
            builder.hostnameVerifier((hostname, session) -> true);
            builder.sslSocketFactory(socketFactory, new HttpsUtils.UnSafeTrustManager());
        }
        OkHttpClient client = builder.build();
        ObjectApiService osChinaApiService = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(ObjectApiService.class);
        Log.d("xf", osChinaApiService + "xufeng");
        return osChinaApiService;
    }

    @Singleton
    @Provides
    EbookApiService provideEbookService() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder().
                connectTimeout(60, TimeUnit.SECONDS).
                readTimeout(60, TimeUnit.SECONDS);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT_WATCH) {
            SSLContext sslContext = null;
            try {
                sslContext = SSLContext.getInstance("TLS");
                try {
                    sslContext.init(null, null, null);
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            SSLSocketFactory socketFactory = new Tls12SocketFactory(sslContext.getSocketFactory());
            builder.hostnameVerifier((hostname, session) -> true);
            builder.sslSocketFactory(socketFactory, new HttpsUtils.UnSafeTrustManager());
        }
        OkHttpClient client = builder.build();
        EbookApiService ebookApiService = new Retrofit.Builder()
                .baseUrl("http://api.zhuishushenqi.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(EbookApiService.class);
        Log.d("xf", "ebookApiService  xufeng");
        return ebookApiService;
    }

    @Provides
    @Singleton
    @Named("EntityManager")
    public BaseDao providerDao() {
        return new EntityManager();
    }

}
