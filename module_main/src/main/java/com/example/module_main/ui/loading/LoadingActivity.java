package com.example.module_main.ui.loading;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.example.module_main.R;
import com.example.module_main.ui.main.MainActivity;
import com.orhanobut.logger.Logger;
import com.zl.common_base.base.BaseActivity;

/***
 * 应用启动页
 */
public class LoadingActivity extends BaseActivity {

    private long loadingTime = 1500;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mHandler.sendEmptyMessageDelayed(0,loadingTime);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(mContext, MainActivity.class));
            finish();
        }
    };

    @Override
    protected int getLayoutId() {
        Logger.d("应用启动页 ");
        return R.layout.main_activity_loading;
    }

    @Override
    protected void initView() {

    }
}
