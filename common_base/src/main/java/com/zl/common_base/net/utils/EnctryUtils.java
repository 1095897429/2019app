package com.zl.common_base.net.utils;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/***
 * 此部分为壁纸项目中加密逻辑，以前放在OkHttpHelper中，现在单独拿出来
 */
public class EnctryUtils {

    /** 统一添加公共参数在请求体中  将公共 + 必须参数 aes加密 ，得到sign值 ---> 将公共 + 必须参数 + sign aes加密，得到数据 ---> hashmap.put(data,得到数据)*/
    public static RequestBody getRequestBody(Map<String,Object> paramMap){
        Gson gson = new Gson();
        HashMap<String,Object> hashMap = new HashMap<>();
        //具体的参数
        if(paramMap != null && !paramMap.isEmpty()){
            for(Map.Entry<String, Object> entry: paramMap.entrySet()) {
                hashMap.put(entry.getKey(),entry.getValue());
            }
        }
        hashMap.put("fromPlat", "发布版本的渠道");
        hashMap.put("appVersion", "发布版本的版本号");
        hashMap.put("deviceId", "发布版本的设备ID");
        hashMap.put("deviceType", "android");
        hashMap.put("timestamp", System.currentTimeMillis() + "");

        String enctry = gson.toJson(hashMap);
        String result = AesUtils.encrypt(enctry,AesUtils.SECRETKEY,AesUtils.IV);
        Logger.d("加密数据：" + result);
        hashMap.put("sign", result);

        String strEntity = gson.toJson(hashMap);
        Logger.d("加过密之后的sign数据：" + strEntity);


        String lastResult = AesUtils.encrypt(strEntity,AesUtils.SECRETKEY,AesUtils.IV);
        Logger.d("加过密之后的发送数据：" + lastResult);

        String dectry =  AesUtils.decrypt(lastResult,AesUtils.SECRETKEY,AesUtils.IV);
        Logger.d("解密 =  " +  dectry);

        hashMap.clear();
        hashMap.put("data",lastResult);

        String lastStrEntity = gson.toJson(hashMap);

        RequestBody requestBody =  RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), lastStrEntity);
        return requestBody;
    }

}
