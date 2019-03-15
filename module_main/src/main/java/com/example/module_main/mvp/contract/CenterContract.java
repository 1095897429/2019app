package com.example.module_main.mvp.contract;

import com.example.module_main.bean.AppInfo;
import com.example.module_main.bean.Template;
import com.zl.common_base.mvp.IBaseView;

import java.util.List;

/***
 * 契约类
 */
public interface CenterContract {

    interface Presenter{

        /** 获取数据 */
        void getList();
    }

    interface View extends IBaseView{

        /** 数据列表 */
        void getList(List<Template> blockList);
    }
}
