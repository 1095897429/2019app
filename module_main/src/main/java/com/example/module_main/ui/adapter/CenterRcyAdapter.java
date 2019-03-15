package com.example.module_main.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_main.R;
import com.example.module_main.bean.Template;

import java.util.List;

public class CenterRcyAdapter extends BaseQuickAdapter<Template,BaseViewHolder> {

    public CenterRcyAdapter(@Nullable List<Template> data) {
        super(R.layout.main_item_of_center_list,data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Template item) {
        holder.setText(R.id.tv_title, item.getTitle());
        holder.setText(R.id.tv_describe, item.getDescribe());
    }
}