package com.zl.common_base.net.helper;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.zl.common_base.net.api.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.zl.common_base.constants.Constants.BASEURL;

public class RetrofitHelper {

    private Retrofit mRetrofit;
    private static RetrofitHelper instance;
    private static ApiService mApiService;


    private RetrofitHelper(){}

    public static RetrofitHelper getInstance(){
        if(instance == null){
            synchronized(RetrofitHelper .class){
                if(instance == null){
                    instance = new RetrofitHelper ();
                }
            }
        }
        return instance;
    }

    public Retrofit getRetrofit(){
        if(null == mRetrofit){
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .client(OkHttpHelper.getInstance().getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            mRetrofit = builder.build();
        }
        return mRetrofit;
    }


    /** 通过泛型方法  获取Service代理对象 */
    public <T> T createService(Class<T> tClass){
        return instance.getRetrofit().create(tClass);
    }



    /** 获取Service代理对象 -- 不涉及模块化时使用 */
    public static ApiService getApiService(){
        if(null == mApiService){
            mApiService = getInstance().getRetrofit().create(ApiService.class);
        }
        return mApiService;
    }



    /** ------------------------ 测试调用案例 -------------------------- */




}
