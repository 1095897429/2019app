package com.zl.common_base.base;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogcatLogStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.zl.common_base.BuildConfig;
import com.zl.common_base.manage.ActivityManage;
import com.zl.common_base.manage.CrashHandlerManage;

/***
 * 基础的Application 所有模块化开发module需要继承自 BaseApplication
 * 1.全局唯一性
 * 2.Activity管理器
 */
public class BaseApplication extends Application {

    //全局唯一的context
    private static BaseApplication application;

    //Activity管理器
    private ActivityManage mActivityManage;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        application = this;
        //MultiDex 分包方法，必须最先初始化
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mActivityManage = new ActivityManage();
        initLogger();
        initCrashManage();
    }

    /** 初始化崩溃管理器 */
    private void initCrashManage() {
        if(!BuildConfig.DEBUG){
            CrashHandlerManage.getInstance().init(getApplicationContext());
        }
    }

    /** 初始化日志打印框架 */
    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)                   //（可选）是否显示线程信息。 默认值为true
                .methodCount(2)                          //（可选）要显示的方法行数。 默认2
                .methodOffset(7)                         //（可选）设置调用堆栈的函数偏移值，0的话则从打印该Log的函数开始输出堆栈信息，默认是0
                .logStrategy(new LogcatLogStrategy())    //（可选）更改要打印的日志策略。 默认LogCat
                .tag("AMD")                              //（可选）每个日志的全局标记。 默认PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy){
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                //DEBUG模式下打印LOG
                return BuildConfig.DEBUG;
            }
        });
    }

    /** 获取全局唯一上下文 */
    public static BaseApplication getApplication() {
        return application;
    }


    /** 返回Activity管理器 */
    public ActivityManage getActivityManage(){
        if(null == mActivityManage){
            mActivityManage = new ActivityManage();
        }
        return mActivityManage;
    }

    /** 退出应用 */
    public void exitApp(){
        mActivityManage.finishAll();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /** 此回调函数只发生在模拟器上，真机不执行,demo中的不可信 */
    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
