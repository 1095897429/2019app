package com.example.module_slipt.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_slipt.R;
import com.example.module_slipt.bean.SliptWeafareBean;

import java.util.List;

/***
 * 福利社适配器
 */
public class SliptWeafareAdapter extends BaseQuickAdapter<SliptWeafareBean,BaseViewHolder> {


    public SliptWeafareAdapter(@Nullable List<SliptWeafareBean> data) {
        super(R.layout.slipt_weafare_task_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SliptWeafareBean item) {

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setText(R.id.content,mData.get(position).getTitle());
    }
}
