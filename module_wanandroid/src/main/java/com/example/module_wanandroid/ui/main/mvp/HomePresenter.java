package com.example.module_wanandroid.ui.main.mvp;


import com.example.module_wanandroid.ui.main.mvp.contract.HomeContract;
import com.zl.common_base.mvp.RxPresenter;

/***
 * 首页的Presenter
 */
public class HomePresenter extends RxPresenter<HomeContract.View> implements
        HomeContract.Presenter {

    @Override
    public void getBanner() {

    }
}
