package com.zl.common_base.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zl.common_base.mvp.IBaseView;
import com.zl.common_base.mvp.RxPresenter;

/***
 * 所有需要MVP开发的Activity的基类,本质是个View
 */

public abstract class BaseMvpActivity<P extends RxPresenter> extends BaseActivity implements IBaseView {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        initInject();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null != mPresenter){
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    private void initInject() {
        if(null != mPresenter){
            mPresenter.attachView(this);
        }
    }

    /** 创建Presenter */
    protected abstract P createPresenter();


    //***************************************IBaseView方法实现*************************************


    @Override
    public void showLoading() {
        if(null != mLoadingDialog && !mLoadingDialog.isShowing()){
            mLoadingDialog.show();
        }
    }

    @Override
    public void dismissLoading() {
        if(null != mLoadingDialog && mLoadingDialog.isShowing()){
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void onEmpty(Object tag) {

    }

    @Override
    public void onError(Object tag, String errorMsg) {

    }

    @Override
    public Context getContext() {
        return mContext;
    }

    //***************************************IBaseView方法实现*************************************
}
