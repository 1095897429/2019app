package com.example.module_wanandroid.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_wanandroid.ApiWanService;
import com.example.module_wanandroid.R;
import com.example.module_wanandroid.R2;
import com.example.module_wanandroid.bean.AdBean;
import com.example.module_wanandroid.bean.Article;
import com.example.module_wanandroid.bean.BannerInfo;
import com.example.module_wanandroid.ui.main.adapter.ArticleAdapter;
import com.example.module_wanandroid.ui.main.mvp.HomePresenter;
import com.example.module_wanandroid.ui.main.mvp.contract.HomeContract;
import com.orhanobut.logger.Logger;
import com.zl.common_base.base.BaseMvpFragment;
import com.zl.common_base.base.BaseSubscriber;
import com.zl.common_base.constants.ARouterConfig;
import com.zl.common_base.net.helper.RetrofitHelper;
import com.zl.common_base.utils.ActivityToActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/***
 * wanAndroid首页
 * 1.布局：banner + recyclerview + recyclerview(文章)
 */
@Route(path = ARouterConfig.WAN_MAIN_FRAGMENT)
public class WanHomeFragment extends BaseMvpFragment<HomePresenter> implements
        BaseQuickAdapter.OnItemClickListener, HomeContract.View{

    @BindView(R2.id.rv_list)
    RecyclerView mArticleRecyclerView;//文章列表

    private ArticleAdapter articleAdapter;
    private List<Article> articleList = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("WanHomeFragment onCreate");


        //TODO 3.1 测试list
//        getHotSearchList();
    }

    private void getHotSearchList() {
        RetrofitHelper.getInstance()
                .createService(ApiWanService.class)
                .getAdHotData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseSubscriber<List<AdBean>>() {
                    @Override
                    public void onSuccess(List<AdBean> adBeanList) {
                        Logger.d("返回的信息：" + adBeanList.size());
                    }

                    @Override
                    public void onFailure(String message) {
                        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.wan_fragment_main;
    }

    @Override
    protected void initView() {
        articleAdapter = new ArticleAdapter(articleList);
        mArticleRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mArticleRecyclerView.setAdapter(articleAdapter);
        mArticleRecyclerView.setNestedScrollingEnabled(false);

        articleAdapter.setOnItemClickListener(this);

        //请求
        mPresenter.getArticleList();
    }

    @Override
    public void bannerList(List<BannerInfo> banners) {

    }

    @Override
    public void articleList(List<Article> articles) {
        articleList.addAll(articles);
        articleAdapter.notifyDataSetChanged();
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }


    //点击事件
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if(view.getId() == R.id.ll_article){
            //点击文章
            ActivityToActivity.toWebView(mContext,articleList.get(position).getLink());
        }
    }
}
