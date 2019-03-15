package com.zl.common_base.base;

import com.google.gson.JsonParseException;
import com.orhanobut.logger.Logger;
import com.zl.common_base.bean.HttpResponse;
import com.zl.common_base.mvp.IBaseView;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

/***
 *
 * @param <T> 定义接口中的实体,给予父类的是HttpResponse<T>,这样onNext返回好解析
 *
 * 1.数据正常返回 {"code":200,"success":true} -- 回调onSuccess
 * 2.数据不正常   {"code":400,"success":false} -- 调用onFailure
 * 3.服务器连接超时 xxxException -- 回调onError
 */
public abstract class BaseSubscriber<T> extends ResourceSubscriber<HttpResponse<T>> {

    private IBaseView mIBaseView;

    /** 通过构造方法注入 */
    public BaseSubscriber(IBaseView mIBaseView){
        this.mIBaseView = mIBaseView;
    }

    public BaseSubscriber(){
    }




    @Override
    protected void onStart() {
        super.onStart();
        //检查网络，取消订阅
    }

    @Override
    public void onNext(HttpResponse<T> response) {
        if(response.getCode() == 200){
            if(response.getData() != null){
                onSuccess(response.getData());
            }
        }else{
            onFailure(response.getCode(),response.getMessage());
        }
    }


    /** 服务器返回数据，但响应码不为200*/
    public void onFailure(int code, String message) {
        Logger.d("通过toast显示： ----  code : ", code + " " + message);
    }

    /** exception返回的提示*/
    public void onFailure(String message) {
        Logger.d("通过toast显示： ----  code : ",  " " + message);
    }


    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable t) {
        Logger.d("错误❎的信息：" + t.getMessage());
        if(t instanceof HttpException){ //   HTTP错误
            onException(ExceptionReason.BAD_NETWORK);
        }else if(t instanceof ConnectException
                || t instanceof UnknownHostException){//  连接错误
            onException(ExceptionReason.CONNECT_ERROR);
        }else if(t instanceof InterruptedException){//  连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT);
        } else if(t instanceof JsonParseException
                || t instanceof JSONException
                || t instanceof ParseException){ //  解析错误
            onException(ExceptionReason.PARSE_ERROR);
        }else if(t instanceof SocketException){//  服务器响应超时
            onException(ExceptionReason.RESPONSE_TIMEOUT);
        }else{
            onException(ExceptionReason.UNKNOWN_ERROR);//未知错误
        }
    }


    public void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                onFailure("可以提供toast显示 ：连接超时");
                break;

            case CONNECT_TIMEOUT:
                Logger.d("连接错误");
                break;

            case BAD_NETWORK:
                Logger.d("HTTP错误");
                break;

            case PARSE_ERROR:
                Logger.d("解析错误");
                break;

            case UNKNOWN_ERROR:
                Logger.d("未知错误");
            default:
                break;
        }
    }


    /** --------------------- 抽象方法 -------------------- */
    public abstract void onSuccess(T t);

    /** --------------------- 枚举 -------------------- */

    public enum ExceptionReason {
        PARSE_ERROR,   /** 解析数据失败 */
        BAD_NETWORK, /** 网络问题 */
        CONNECT_ERROR, /** 连接错误 */
        CONNECT_TIMEOUT, /** 连接超时 */
        RESPONSE_TIMEOUT, /** 响应超时 */
        UNKNOWN_ERROR, /** 未知错误 */
    }




}
