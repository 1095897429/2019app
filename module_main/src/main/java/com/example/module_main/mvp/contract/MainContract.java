package com.example.module_main.mvp.contract;

import com.example.module_main.bean.AppInfo;
import com.example.module_main.bean.Template;
import com.zl.common_base.mvp.IBaseView;

import java.util.List;

/***
 * 契约类
 */
public interface MainContract {

    interface Presenter{
        /** 检查更新 */
        void checkUpdate();

        /** 获取控件tab数据 */
        void getTabList();
    }

    interface View extends IBaseView{
        /** 需要更新 */
        void needUpdate(AppInfo appInfo);

        /** 控件tab数据列表 */
        void tabList(List<Template> blockList);
    }
}
