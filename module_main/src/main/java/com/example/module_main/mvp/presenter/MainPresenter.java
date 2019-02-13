package com.example.module_main.mvp.presenter;

import com.example.module_main.bean.AppInfo;
import com.example.module_main.bean.Template;
import com.example.module_main.mvp.contract.MainContract;
import com.zl.common_base.mvp.RxPresenter;

import java.util.ArrayList;
import java.util.List;

/***
 * 首页Presenter
 */
public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    @Override
    public void checkUpdate() {
        if(null != getView()){
            AppInfo appInfo = new AppInfo();
            getView().needUpdate(appInfo);
        }
    }

    @Override
    public void getTabList() {
        if(null != getView()){
            //伪数据
            List<Template> list = new ArrayList<>();
            getView().tabList(list);
        }
    }
}
