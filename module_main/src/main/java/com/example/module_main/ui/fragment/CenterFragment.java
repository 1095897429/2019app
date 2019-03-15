package com.example.module_main.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_main.R;
import com.example.module_main.R2;
import com.example.module_main.bean.Template;
import com.example.module_main.mvp.contract.CenterContract;
import com.example.module_main.mvp.presenter.CenterPresenter;
import com.example.module_main.ui.adapter.CenterRcyAdapter;
import com.example.module_main.ui.customview.firstday.FirstDayActivity;
import com.orhanobut.logger.Logger;
import com.zl.common_base.base.BaseMvpFragment;
import com.zl.common_base.constants.ARouterConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/***
 * 中间的Fragment
 */
@Route(path = ARouterConfig.CENTER_FRAGMENT)
public class CenterFragment extends BaseMvpFragment<CenterPresenter>
        implements CenterContract.View {


    @BindView(R2.id.recycle_view)
    RecyclerView recyclerView;


    private List<Template> data = new ArrayList<>();
    private CenterRcyAdapter mCenterRcyAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.center_fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("CenterFragment onCreate");
    }

    @Override
    protected void initView() {

        initData();

        mPresenter.getList();
    }

    private void initData() {
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));

        mCenterRcyAdapter = new CenterRcyAdapter(data);
        recyclerView.setAdapter(mCenterRcyAdapter);
        recyclerView.setNestedScrollingEnabled(false);


        mCenterRcyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        startActivity(new Intent(mContext,FirstDayActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    public void getList(List<Template> blockList) {
        mCenterRcyAdapter.setNewData(blockList);
    }

    @Override
    protected CenterPresenter createPresenter() {
        return new CenterPresenter();
    }
}
