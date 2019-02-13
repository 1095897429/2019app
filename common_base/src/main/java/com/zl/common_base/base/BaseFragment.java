package com.zl.common_base.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.zl.common_base.R;
import com.zl.common_base.widget.LoadingDialog;

import org.w3c.dom.Text;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/***
 * 所有Fragment的基类
 * 1.默认空布局隐藏 + 内容布局
 */
public abstract class BaseFragment extends Fragment {

    private ViewStub emptyView;
    private Unbinder mUnbinder;
    private View rootView;
    protected Context mContext;
    protected LoadingDialog mLoadingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mLoadingDialog = new LoadingDialog(mContext);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_base,container,false);
        ((ViewGroup)rootView.findViewById(R.id.fl_content)).addView(getLayoutInflater().inflate(getLayoutId(),null));
        //初始化ButterKnife
        mUnbinder = ButterKnife.bind(this,rootView);
        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(null != mUnbinder){
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }

    //***************************************空页面方法*************************************

    /** 隐藏空数据视图 */
    protected void hideEmptyView(){
        if(null != emptyView){
            emptyView.setVisibility(View.GONE);
        }
    }

    /** 显示空数据视图 */
    protected void showEmptyView(){
        showEmptyOrErrorView(getString(R.string.no_data),R.drawable.bg_no_data);
    }

    /** 显示错误数据视图 */
    protected void showErrorView(){
        showEmptyOrErrorView(getString(R.string.error_data),R.drawable.bg_no_net);
    }

    private void showEmptyOrErrorView(String text,int img){
        if(null != emptyView){
            emptyView = rootView.findViewById(R.id.vs_empty);
        }
        //通过visible让其显示
        emptyView.setVisibility(View.VISIBLE);
        //设置图片
        rootView.findViewById(R.id.iv_empty).setBackgroundResource(img);
        ((TextView) rootView.findViewById(R.id.tv_empty)).setText(text);
        //点击空白处
        rootView.findViewById(R.id.ll_empty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPageClick();
            }
        });
    }


    /** 空页面被点击 */
    protected void onPageClick(){}

    //***************************************空页面方法*************************************

    protected abstract int getLayoutId();

    protected abstract void initView();
}
