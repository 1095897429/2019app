package com.example.module_wanandroid.ui.main.mvp;


import com.example.module_wanandroid.bean.Article;
import com.example.module_wanandroid.ui.main.mvp.contract.HomeContract;
import com.zl.common_base.mvp.RxPresenter;

import java.util.ArrayList;
import java.util.List;

/***
 * 首页的Presenter
 */
public class HomePresenter extends RxPresenter<HomeContract.View> implements
        HomeContract.Presenter {

    @Override
    public void getBanner() {

    }

    @Override
    public void getArticleList() {
        getView().showLoading();//显示dialog


        try {
            Thread.sleep(2000);
            //伪正常数据
            List<Article> articles = createFakeList();
            getView().articleList(articles);
            getView().dismissLoading();//销毁dialog
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private List<Article> createFakeList() {
        List<Article> articles = new ArrayList<>();
        Article article;
        for (int i = 0; i < 10; i++) {
            article = new Article();
            article.setLink("www.baidu.com");
            articles.add(article);
        }
        return articles;
    }
}
