package com.zl.common_base.mvp;

/***
 * 所有Presenter基类
 * 1.绑定/解绑 View
 * 2.提供getView方法暴露view
 */
public class RxPresenter<V extends IBaseView> {

    private V mView;

    /**  绑定View */
    public void attachView(V view){
        mView = view;
    }


    /**  解绑View */
    public void detachView(){
        mView = null;
    }

    /** 可能网络正在请求，activity已经销毁了，此部分需要优化 */
    protected V getView(){
        return mView;
    }

}
