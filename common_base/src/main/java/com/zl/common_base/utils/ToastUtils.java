package com.zl.common_base.utils;

import android.content.Context;
import android.widget.Toast;

/***
 * Toast
 */
public class ToastUtils {

    public static void showToast(Context context,int strings){
        showToast(context,context.getString(strings));
    }


    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

}
