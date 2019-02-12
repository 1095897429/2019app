package com.zl.common_base.manage;

import android.app.Activity;

import java.util.HashSet;
import java.util.Set;

/***
 * 管理所有的Activity
 */
public class ActivityManage {

    //保存所有创建的Activity
    private Set<Activity> allActivities = new HashSet<>();

    /** 添加 Activity */
    public void addActivity(Activity activity){
        if(null != activity){
            allActivities.add(activity);
        }
    }

    /** 移除 Activity */
    public void removeActivity(Activity activity){
        if(null != activity){
            allActivities.remove(activity);
        }
    }

    /** 关闭所有 Activity */
    public void finishAll(){
        for (Activity activity : allActivities) {
            activity.finish();
        }
    }

}
