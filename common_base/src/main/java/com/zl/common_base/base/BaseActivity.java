package com.zl.common_base.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;

import com.zl.common_base.widget.LoadingDialog;

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
        //初始化dialog
        mLoadingDialog = new LoadingDialog(this);
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
    }

    protected abstract int getLayoutId() ;

    protected abstract void initView() ;


}
