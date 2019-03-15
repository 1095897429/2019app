package com.zl.common_base.net.api;


import io.reactivex.Flowable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/***
 * 1.发送给后台的是json字符串
 * @Body  -- 将数据添加到requestBody中
 * 此接口为当时做壁纸项目保存的
 */

public interface ApiResBodyService {

    @POST("ads/openAd")
    Flowable<ResponseBody> openAd(@Body RequestBody requestBody);// 开屏广告接口

    @POST("user/initUserInfo")
    Flowable<ResponseBody> initUserInfo(@Body RequestBody requestBody);//用户信息初始化

    @POST("wallpaper/index")
    Flowable<ResponseBody> index(@Query("page") int page, @Body RequestBody requestBody);//首页数据


    @POST("wallpaper/search")
    Flowable<ResponseBody> search(@Query("page") int page, @Body RequestBody requestBody);//搜索壁纸


    @POST("ads/searchPage")
    Flowable<ResponseBody> searchPage(@Body RequestBody requestBody);//搜索页内容


    @POST("ads/navigation")
    Flowable<ResponseBody> navigationList(@Query("page") int page, @Body RequestBody requestBody);//导航专题列表页


    @POST("ads/hotSearch")
    Flowable<ResponseBody> hotSearchList(@Query("page") int page, @Body RequestBody requestBody);//热搜列表页


    @POST("ads/banner")
    Flowable<ResponseBody> banner(@Body RequestBody requestBody);//banner专题详情页数据


    @POST("wallpaper/list")
    Flowable<ResponseBody> wallpagerList(@Query("page") int page, @Body RequestBody requestBody);//壁纸列表页数据


    @POST("wallpaper/categories")
    Flowable<ResponseBody> categories(@Body RequestBody requestBody);//分类中兴趣的壁纸分类集合



    @POST("user/thirdPlatLogin")
    Flowable<ResponseBody> thirdPlatLogin(@Body RequestBody requestBody);//第三方登录


    @POST("wallpaper/uploadHistory")
    Flowable<ResponseBody> uploadHistory(@Query("access-token") String accessToken,
                                         @Body RequestBody requestBody);//用户上传壁纸历史

    @POST("history/getRecord")
    Flowable<ResponseBody> getRecord(@Body RequestBody requestBody);//用户下载、分享、收藏壁纸信息



    @POST("user/updateUserHeadImage")
    Flowable<ResponseBody> uploadHeadImage(@Query("access-token") String accessToken, @Body RequestBody requestBody);//修改用户头像

    @POST("user/updateUserInfo")
    Flowable<ResponseBody> updateUserInfo(@Query("access-token") String accessToken, @Body RequestBody requestBody);//修改用户信息


    @POST("wallpaper/categoryList")
    Flowable<ResponseBody> categoryList(@Body RequestBody requestBody);//开始感兴趣的壁纸分类集合


    @POST("wallpaper/getUploadToken")
    Flowable<ResponseBody> getUploadToken(@Body RequestBody requestBody);//获取图片上传的token


    @POST("wallpaper/upload")
    Flowable<ResponseBody> uploadWallpaper(@Query("access-token") String accessToken,
                                           @Body RequestBody requestBody);//用户上传壁纸

    @POST("wallpaper/detail")
    Flowable<ResponseBody> detail(@Body RequestBody requestBody);//壁纸详情页


    @POST("history/record")
    Flowable<ResponseBody> record(@Body RequestBody requestBody);// 记录用户下载，收藏，分享壁纸


    @POST("history/deleteCollection")
    Flowable<ResponseBody> deleteCollection(@Body RequestBody requestBody);//取消收藏

    @POST("ads/alertAd")
    Flowable<ResponseBody> alertAd(@Body RequestBody requestBody);//下载弹出广告


    @POST("history/report")
    Flowable<ResponseBody> report(@Body RequestBody requestBody);//记录用户举报


    @POST("user/interestCategory")
    Flowable<ResponseBody> interestCategory(@Body RequestBody requestBody);//用户选择感兴趣的壁纸分类


    @POST("user/login")
    Flowable<ResponseBody> loginPhone(@Body RequestBody requestBody);//手机登录


    @POST("mobile-code/sendMobileCode")
    Flowable<ResponseBody> sendMobileCode(@Body RequestBody requestBody);//验证码


    @POST("history/searchHistory")
    Flowable<ResponseBody> searchHistory(@Body RequestBody requestBody);//记录用户最近5次关键字搜索历史



    /** ------------------------------------------------------------------------------------------------------ */













}
