package com.example.module_wanandroid.ui.main.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_wanandroid.R;
import com.example.module_wanandroid.bean.Article;

import java.util.List;

/***
 * 文章列表适配器
 */
public class ArticleAdapter extends BaseQuickAdapter<Article,BaseViewHolder>{

    public ArticleAdapter(@Nullable List<Article> data) {
        super(R.layout.wan_item_of_article_list,data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Article item) {
        holder.setText(R.id.tv_title,item.getTitle());
        holder.setText(R.id.tv_author,item.getAuthor());
        holder.setText(R.id.tv_date,String.format("时间:%s",item.getNiceDate()));

        if(!TextUtils.isEmpty(item.getChapterName())){
            holder.setText(R.id.tv_type1,item.getChapterName());
        }

        if(!TextUtils.isEmpty(item.getSuperChapterName())){
            holder.setText(R.id.tv_type2,item.getSuperChapterName());
        }

    }











}
