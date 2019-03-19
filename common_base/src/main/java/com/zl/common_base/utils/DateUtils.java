package com.zl.common_base.utils;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.zl.common_base.constants.Constants;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
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

    /** 将整的秒数 转换成时分秒，没有时则只显示分秒 3862/3600 = 1小时 余 262秒 --> 262 / 60 = 4分钟 22秒*/
    public static String formatDownTime(int second){
        int hour = 0;
        int min = 0;
        int se = 0;
        if(second >= 3600){
            hour = second / 3600;//获取小时
            int temp = second % 3600;
            if(temp != 0){
                if(temp > 60){//👆的262秒
                    min = temp / 60;
                    if(temp % 60 != 0){
                        se = temp % 60;
                    }
                }else{
                    se = temp;
                }
            }
        }else{
            min = second / 60;
            if(second % 60 != 0){
                se = second % 60;
            }
        }

        StringBuilder builder = new StringBuilder();
        if(hour == 0){//如果hour为空则不显示
            builder.append("00").append(":");
        }else if(hour < 10){
            builder.append("0");
            builder.append(hour).append(":");
        }

        if(min == 0){
            builder.append("00").append(":");
        }else if(min < 10){
            builder.append("0");
        }
        builder.append(min).append(":");

        if(se < 10){
            builder.append("0");
        }

        builder.append(se);

        return builder.toString();
    }


    /**
     * 根据时间戳来判断当前的时间是几天前,几分钟,刚刚
     * @param long_time
     * @return
     */
    public static String getTimeStateNew(String long_time){
        String long_by_13="1000000000000";
        String long_by_10="1000000000";
        if(Long.valueOf(long_time)/Long.valueOf(long_by_13)<1){
            if(Long.valueOf(long_time)/Long.valueOf(long_by_10)>=1){
                long_time=long_time+"000";
            }
        }
        Timestamp time=new Timestamp(Long.valueOf(long_time));
        Timestamp now=new Timestamp(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//    System.out.println("传递过来的时间:"+format.format(time));
//    System.out.println("现在的时间:"+format.format(now));
        long day_conver=1000*60*60*24;
        long hour_conver=1000*60*60;
        long min_conver=1000*60;
        long time_conver=now.getTime()-time.getTime();
        long temp_conver;
//    System.out.println("天数:"+time_conver/day_conver);
        if((time_conver/day_conver)<3){
            temp_conver=time_conver/day_conver;
            if(temp_conver<=2 && temp_conver>=1){
                return temp_conver+"天前";
            }else{
                temp_conver=(time_conver/hour_conver);
                if(temp_conver>=1){
                    return temp_conver+"小时前";
                }else {
                    temp_conver=(time_conver/min_conver);
                    if(temp_conver>=1){
                        return temp_conver+"分钟前";
                    }else{
                        return "刚刚";
                    }
                }
            }
        }else{
            return format.format(time);
        }
    }


}
