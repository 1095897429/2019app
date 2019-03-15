package com.zl.common_base.net.helper;


import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by zl on 2018/5/22.
 * okHttp 帮助类
 */

public class OkHttpHelper {

    private static final long DEFAULT_READ_TIMEOUT_MILLIS = 20 * 1000;  //读取时间
    private static final long DEFAULT_WRITE_TIMEOUT_MILLIS = 20 * 1000; //写入时间
    private static final long DEFAULT_CONNECT_TIMEOUT_MILLIS = 20 * 1000; //超时时间

    private static OkHttpHelper sInstance;//单例
    private OkHttpClient mOkHttpClient;//依赖OkHttpClient

    public static OkHttpHelper getInstance() {
        if (sInstance == null) {
            synchronized (OkHttpHelper.class) {
                if (sInstance == null) {
                    sInstance = new OkHttpHelper();
                }
            }
        }
        return sInstance;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    private OkHttpHelper(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        mOkHttpClient = builder
                .readTimeout(DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true) // 失败重发
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

    }




}
