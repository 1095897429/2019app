package com.example.module_main.mvp.presenter;

import com.example.module_main.bean.AppInfo;
import com.example.module_main.bean.Template;
import com.example.module_main.mvp.contract.CenterContract;
import com.example.module_main.mvp.contract.MainContract;
import com.zl.common_base.mvp.RxPresenter;

import java.util.ArrayList;
import java.util.List;

/***
 * 首页Presenter，这里view指的是具体的View
 */
public class CenterPresenter extends RxPresenter<CenterContract.View> implements CenterContract.Presenter {



    @Override
    public void getList() {
        if(null != getView()){
            //伪数据
            List<Template> list = new ArrayList<>();
            list.add(new Template("第一天", "2019.2.15 仿支付宝芝麻信用 -- https://blog.csdn.net/ccy0122/article/details/53241648"));
            list.add(new Template("第二天", "2019.2.16 仿时钟 -- https://www.jianshu.com/p/86e867b9bee8"));
            list.add(new Template("第三天", "2019.2.17 仿自动换行标签容器 -- https://blog.csdn.net/u013651026/article/details/78830930"));
            list.add(new Template("第四天", "2019.2.18 viewgroup中🈶4个view -- https://blog.csdn.net/lmj623565791/article/details/38339817"));
            list.add(new Template("第五天", "2019.2.19 仿qq下拉抢红包 -- https://blog.csdn.net/wingichoy/article/details/50662592"));
            list.add(new Template("第六天", "2019.2.20 回顾之前5天做的东西以及知识点"));
            list.add(new Template("第⑦天", "2019.2.21 nothing"));
            list.add(new Template("第8天", "2019.2.22 nothing"));

            //TODO 关注 三精-大精wing https://me.csdn.net/wingichoy
            getView().getList(list);
        }
    }
}
