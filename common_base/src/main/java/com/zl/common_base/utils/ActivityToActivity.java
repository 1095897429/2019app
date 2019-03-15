package com.zl.common_base.utils;

import android.content.Context;

import com.zl.common_base.activity.WebViewActivity;

/***
 * Activity跳转
 */
public class ActivityToActivity {

    /**
     * 跳转普通WebView
     *
     * @param activity activity
     * @param url      目标Url
     */
    public static void toWebView(Context activity,String url){
        WebViewActivity.actionStart(activity,url);
    }
}
