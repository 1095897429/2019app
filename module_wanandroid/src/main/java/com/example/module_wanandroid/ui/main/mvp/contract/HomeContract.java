package com.example.module_wanandroid.ui.main.mvp.contract;

import com.example.module_wanandroid.bean.Article;
import com.example.module_wanandroid.bean.BannerInfo;
import com.zl.common_base.mvp.IBaseView;

import java.util.List;

/***
 * wanAndroid首页契约类
 */
public interface HomeContract {

    interface Presenter{
        /** 获取Banner */
        void getBanner();

        /** 获取文章列表 */
        void getArticleList();
    }

    interface View extends IBaseView{
        /** Banner列表 */
        void bannerList(List<BannerInfo> banners);

        void articleList(List<Article> articles);
    }
}
