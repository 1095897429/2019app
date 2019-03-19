package com.example.module_slipt.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_slipt.R;
import com.example.module_slipt.bean.SliptTaskBean;

import java.util.List;

/***
 * 限时拆红包适配器
 */
public class SliptLimitRedAdapter extends BaseQuickAdapter<SliptTaskBean,BaseViewHolder> {


    public SliptLimitRedAdapter(@Nullable List<SliptTaskBean> data) {
        super(R.layout.slipt_limit_red_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SliptTaskBean item) {

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        SliptTaskBean bean = mData.get(position);
        //判断红包🧧状态 0-去完成；1-领取奖励；2-已完成
        if(bean.getDone_status() == 0){
            holder.setText(R.id.status_title,"拆");
            holder.setText(R.id.status_text,bean.getTitle() + bean.getDone_num() + "/" + bean.getNum());
            holder.setVisible(R.id.status_lock,false);
        }else if(bean.getDone_status() == 1){
            holder.setText(R.id.status_title,"领");
            holder.setVisible(R.id.status_text,false);
            holder.setVisible(R.id.status_lock,false);
        }else if(bean.getDone_status() == 2){
            holder.setVisible(R.id.status_title,false);
            holder.setVisible(R.id.status_lock,true);
            holder.setText(R.id.status_text,"不知道🙅‍♂️写啥");
        }
    }
}
