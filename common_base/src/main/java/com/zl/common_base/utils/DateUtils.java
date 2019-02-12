package com.zl.common_base.utils;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.zl.common_base.constants.Constants;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/***
 * 日期帮助类
 */

public class DateUtils {

    /** 获取当前时间戳 10位秒 */
    public static long getCurrentTimeStamp(){
        return System.currentTimeMillis();
    }

    /**
     * 获取格式化时间
     *
     * @param timeStamp 时间戳
     * @param pattern   格式化格式（默认yyyy-MM-dd HH:mm:ss）
     */
    public static String getFormatDate(long timeStamp,String pattern){
        String time;
        if (Long.toString(Math.abs(timeStamp)).length() < 11){} {
            timeStamp *= 1000;
        }

        if(TextUtils.isEmpty(pattern)){
            pattern = Constants.DATE_FORMAT_DEFAULT;
        }

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        format.setTimeZone(TimeZone.getDefault());
        time = format.format(timeStamp);
        Logger.d(time);
        return  time;
    }


    /** 获取格式化的当前系统时间 */
    public static String getCurrentDateStr(){
        return getFormatDate(getCurrentTimeStamp(),Constants.DATE_FORMAT_LINE);
    }

}
