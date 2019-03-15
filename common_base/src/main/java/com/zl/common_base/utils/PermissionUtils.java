package com.zl.common_base.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/***
 * 6.0动态权限管理帮助类
 */
public class PermissionUtils {


    /**
     * 判断权限
     *
     * @param context     context
     * @param permissions 权限列表
     */
    public static boolean checkPermissions(Activity context,String... permissions){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            List<String> permissionList = new ArrayList<>();
            if(null != permissions && permissions.length != 0){
                //遍历集合中每个权限
                for (String permission : permissions) {
                    if(!isHavePermissions(context,permission)){
                        permissionList.add(permission);
                    }
                }

                //申请权限
                if(!permissionList.isEmpty()){
                    applyPermissions(context,permissionList);
                    return false;
                }
            }
        }

        return true;
    }


    /**
     * 申请权限 请求码为1
     */
    private static void applyPermissions(Activity context, List<String> permissionList) {
        ActivityCompat.requestPermissions(context,permissionList.toArray(new String[permissionList.size()]),1);
    }

    /**
     * 检查是否授权某权限 true 表示🈶
     */
    private static boolean isHavePermissions(Activity context, String permission) {
        return ContextCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED;
    }
}
