package com.zl.common_base.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;

import com.orhanobut.logger.Logger;
import com.zl.common_base.R;
import com.zl.common_base.bean.Event;
import com.zl.common_base.widget.LoadingDialog;
import com.zl.common_base.widget.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/***
 * 所有Activity的基类
 * 1.ButterKnife初始化与解绑
 */
public abstract class BaseActivity  extends FragmentActivity {

    protected Context mContext;
    private Unbinder mUnbinder;
    protected LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        //固定屏幕方向
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置在activity启动的时候输入法输入法显示时平移窗口，类似微信弹框
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //设置布局
        setContentView(getLayoutId());
        //加入Activity管理器中
        BaseApplication.getApplication().getActivityManage().addActivity(this);
        //初始化ButterKnife
        mUnbinder = ButterKnife.bind(this);
        //设置状态栏
        setStatusBar();
        //初始化dialog
        mLoadingDialog = new LoadingDialog(this);
        //注册事件
        if(regEvent()){
            EventBus.getDefault().register(this);
        }
    }


    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
    }


    /** 当view被附着在窗体时触发 */
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //将Activity从管理器移除
        BaseApplication.getApplication().getActivityManage().removeActivity(this);
        //解绑
        if(null != mUnbinder){
            mUnbinder.unbind();
            mUnbinder = null;
        }
        //解绑
        if (regEvent()) {
            EventBus.getDefault().unregister(this);
        }
    }

    protected abstract int getLayoutId() ;

    protected abstract void initView() ;

    //*************************************** eventbus实现*************************************

    /** 需要接收事件 重写该方法 并返回true */
    protected boolean regEvent(){
        return false;
    }

    /** 子类接受事件 重写该方法 */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(Event event){

    }

    //*************************************** eventbus实现*************************************


    //***************************************权限回调实现*************************************

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Logger.e("permissions:" + Arrays.toString(permissions) + " grantResults:" + Arrays.toString(grantResults));

        //如果有任意未授权权限则跳转到设置界面，当然也可以以dialog的形式展示
        if(!requestPermissionResult(grantResults)){
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }

    /**
     * 判断授权结果 -1代表没有授权
     */
    private boolean requestPermissionResult(int[] grantResults) {
        for (int code : grantResults) {
            if(code == -1 ){
                return false;
            }
        }
        return true;
    }


    //***************************************权限回调实现*************************************



}
