package com.example.module_wanandroid.ui.main.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_wanandroid.R;
import com.example.module_wanandroid.bean.BannerInfo;
import com.example.module_wanandroid.ui.main.mvp.HomePresenter;
import com.example.module_wanandroid.ui.main.mvp.contract.HomeContract;
import com.zl.common_base.base.BaseMvpFragment;
import com.zl.common_base.constants.ARouterConfig;

import java.util.List;

/***
 * wanAndroid首页
 */
@Route(path = ARouterConfig.WAN_MAIN_FRAGMENT)
public class WanHomeFragment extends BaseMvpFragment<HomePresenter> implements
            HomeContract.View{

    @Override
    protected int getLayoutId() {
        return R.layout.wan_fragment_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void bannerList(List<BannerInfo> banners) {

    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }


}
