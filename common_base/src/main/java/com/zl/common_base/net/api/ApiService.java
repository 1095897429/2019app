package com.zl.common_base.net.api;

import com.zl.common_base.base.bean.BaseBean;
import com.zl.common_base.bean.HttpResponse;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/***
 * 小鱼赚钱的api
 */
public interface ApiService {

    /**
     * 获取验证码
     *
     * @param mobile 手机号
     * @param type   短信or语音
     */
//    @FormUrlEncoded
//    @POST("androidauth/getcode")
//    Observable<HttpResponse<Object>> getCode(@Field("mobile") String mobile, @Field("type") int type);
//




   //------------------------------------------------------------------------------------------------------------------//


    /**
     * 获取验证码 -- 测试
     *
     * @param mobile 手机号
     */
//    @FormUrlEncoded
//    @POST("mobile-code/send-mobile-code")
//    Flowable<HttpResponse<BaseBean>> getCode(@Field("mobile") String mobile);


}
