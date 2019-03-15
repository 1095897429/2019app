package com.example.module_user;

import com.example.module_user.bean.VerCodeBean;
import com.zl.common_base.base.bean.BaseBean;
import com.zl.common_base.bean.HttpResponse;
import com.zl.common_base.net.api.ApiService;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/***
 * 用户的api
 */
public interface ApiUserService {




    /**
     * 获取验证码 -- 测试
     *
     * @param mobile 手机号
     */
    @FormUrlEncoded
    @POST("mobile-code/send-mobile-code")
    Flowable<HttpResponse<VerCodeBean>> getCode(@Field("mobile") String mobile);


}
