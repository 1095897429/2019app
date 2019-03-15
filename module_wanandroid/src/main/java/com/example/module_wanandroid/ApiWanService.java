package com.example.module_wanandroid;

import com.example.module_wanandroid.bean.AdBean;
import com.zl.common_base.bean.HttpResponse;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/***
 * 小鱼赚钱的api
 */
public interface ApiWanService {

    @GET("ads/hot-search")
    Flowable<HttpResponse<List<AdBean>>> getAdHotData();


}
