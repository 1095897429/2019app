package com.zl.common_base.manage;

import android.content.Context;

/***
 * 当程序发生Uncaught异常的时候，由该类来接管程序，并记录发送错误报告，不依赖于第三方
 */
public class CrashHandlerManage implements Thread.UncaughtExceptionHandler {

    private Context mContext;
    private static CrashHandlerManage INSTANCE;
    private Thread.UncaughtExceptionHandler mDefaultHandler;// 系统默认的UncaughtException处理类

    private CrashHandlerManage() {}

    public synchronized static CrashHandlerManage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CrashHandlerManage();
        }
        return INSTANCE;
    }

    /** 初始化 */
    public void init(Context context) {
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();// 获取系统默认的UncaughtException处理器
        Thread.setDefaultUncaughtExceptionHandler(this);// 设置该CrashHandler为程序的默认处理器
    }


    @Override
    public void uncaughtException(Thread t, Throwable e) {

    }
}
