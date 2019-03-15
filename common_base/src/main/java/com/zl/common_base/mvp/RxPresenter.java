package com.zl.common_base.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/***
 * 所有Presenter基类 -- 称为 "主持人"的基类
 * 1.绑定/解绑 View
 * 2.提供getView方法暴露view
 */
public class RxPresenter<V extends IBaseView> {

    private V mView;

    private CompositeDisposable mCompositeDisposable;

    /**  绑定View */
    public void attachView(V view){
        mView = view;
    }


    /**  解绑View 并 取消订阅 */
    public void detachView(){
        mView = null;
        unSubscribe();
    }

    /** 可能网络正在请求，activity已经销毁了，此部分需要优化 */
    protected V getView(){
        return mView;
    }


    /**  将业务加入到容器中 */
    public void addSubscribe(Disposable disposable){
        if(null == mCompositeDisposable){
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }


    /** dispose() 即可切断所有的水管，使下游收不到事件，就不会去更新UI了 */
    private void unSubscribe() {
        if(mCompositeDisposable != null){
            mCompositeDisposable.dispose();
        }
    }

}
