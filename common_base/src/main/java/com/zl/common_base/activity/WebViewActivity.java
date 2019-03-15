package com.zl.common_base.activity;

import android.content.Context;
import android.content.Intent;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zl.common_base.R;
import com.zl.common_base.R2;
import com.zl.common_base.base.BaseActivity;

import butterknife.BindView;

public class WebViewActivity extends BaseActivity {

    @BindView(R2.id.webview)
    WebView mWebView;


    public static void actionStart(Context context,String url){
        Intent intent = new Intent(context,WebViewActivity.class);
        intent.putExtra("URL",url);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initView() {
        mWebView.loadUrl(getIntent().getStringExtra("URL"));
        mWebView.setWebViewClient(new MyWebClient());
    }


    //主要帮助WebView处理请求事件
    private class MyWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            mWebView.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed(); // 接受所有网站的证书
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }



}
