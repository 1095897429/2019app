package com.example.module_slipt.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_slipt.R;
import com.example.module_slipt.bean.SliptTaskBean;

import java.util.List;

/***
 * 日常任务适配器
 * 1.阅读新闻
 */
public class SliptDayTaskAdapter extends BaseQuickAdapter<SliptTaskBean,BaseViewHolder> {


    public SliptDayTaskAdapter(@Nullable List<SliptTaskBean> data) {
        super(R.layout.slipt_day_task_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SliptTaskBean item) {

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setText(R.id.content,mData.get(position).getTitle());

        //设置子itm的点击事件
        holder.addOnClickListener(R.id.toComple);
    }
}
