package com.example.module_main.ui.main;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.example.module_main.R;
import com.example.module_main.bean.AppInfo;
import com.example.module_main.bean.Template;
import com.example.module_main.mvp.contract.MainContract;
import com.example.module_main.mvp.presenter.MainPresenter;
import com.zl.common_base.base.BaseApplication;
import com.zl.common_base.base.BaseFragment;
import com.zl.common_base.base.BaseMvpActivity;
import com.zl.common_base.constants.ARouterConfig;
import com.zl.common_base.utils.ARouterUtils;
import com.zl.common_base.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/***
 * main程序的主界面
 * 1.首页是玩android模块
 * 2.中心是main模块
 * 3.我的是User模块
 */

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {


    private long mExitTime;

    /** 存放切换Fragment */
    private List<Fragment> mFragmentList = new ArrayList<>();

    //玩Android模块Fragment
    private BaseFragment wanFragment = ARouterUtils.getFragment(ARouterConfig.WAN_MAIN_FRAGMENT);


    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_main;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initView() {
        changeFragment(ARouterConfig.WAN_MAIN_FRAGMENT);
    }


    /***
     * 改变Fragment
     * @param tag
     */
    private void changeFragment(String tag) {
        hideFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //查询Fragment
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if(null != fragment){
            transaction.show(fragment);//显示
        }else{
            if(TextUtils.equals(tag,ARouterConfig.WAN_MAIN_FRAGMENT)){
                fragment = wanFragment;
            }
            mFragmentList.add(fragment);
            transaction.add(R.id.fl_context,fragment,tag);//新增
        }
        transaction.commitNowAllowingStateLoss();
    }

    private void hideFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (Fragment f : mFragmentList ) {
            transaction.hide(f);
        }
        transaction.commit();
    }

    @Override
    public void needUpdate(AppInfo appInfo) {

    }

    @Override
    public void tabList(List<Template> blockList) {

    }


    /** 重写activity 的 onKeyDown */
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(KeyEvent.KEYCODE_BACK == keyCode){
            if((System.currentTimeMillis() - mExitTime > 2000)){
                mExitTime = System.currentTimeMillis();
                ToastUtils.showToast(mContext,getString(R.string.main_exit_app));
            }else{
                BaseApplication.getApplication().exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
