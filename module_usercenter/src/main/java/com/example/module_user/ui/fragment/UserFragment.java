package com.example.module_user.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_user.ApiUserService;
import com.example.module_user.R;
import com.example.module_user.bean.VerCodeBean;
import com.orhanobut.logger.Logger;
import com.zl.common_base.base.BaseFragment;
import com.zl.common_base.base.BaseSubscriber;
import com.zl.common_base.base.bean.BaseBean;
import com.zl.common_base.constants.ARouterConfig;
import com.zl.common_base.net.helper.RetrofitHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


@Route(path = ARouterConfig.USER_FRAGMENT)
public class UserFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("UserFragment onCreate");
    }


    @Override
    protected int getLayoutId() {
        return R.layout.user_fragment;
    }

    @Override
    protected void initView() {

        //TODO 测试返回实体
//        getCode();

    }

    @SuppressLint("CheckResult")
    private void getCode() {
        RetrofitHelper.getInstance()
                .createService(ApiUserService.class)
                .getCode("18616541823")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseSubscriber<VerCodeBean>() {
                    @Override
                    public void onSuccess(VerCodeBean verCodeBean) {
                        Logger.d("返回的信息：" + verCodeBean.getCode());
                    }
                });


    }
}
