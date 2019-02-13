package com.zl.common_base.utils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zl.common_base.base.BaseFragment;

/***
 * ARouter帮助类
 */
public class ARouterUtils {

    /**
     * 根据path返回Fragment
     *
     * @param path path  目标界面对应的路径
     * @return fragment
     */
    public static BaseFragment getFragment(String path){
        return (BaseFragment) ARouter.getInstance()
                .build(path)
                .navigation();
    }
}
