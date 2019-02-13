package com.zl.common_base.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zl.common_base.R;

/***
 * 等待加载框
 */
public class LoadingDialog {

    private Context mContext;
    private boolean cancelable = true;//显示返回键是否有效
    private boolean isShow ;//是否显示
    private String msg = "加载中···";
    private LVCircleRing mLVCircleRing;
    private Dialog mDialog;

    public LoadingDialog(Context context){
        mContext = context;
    }


    public boolean isShowing(){
        return  isShow;
    }

    /** 委托dialog的show */
    public void show(){
        View view = View.inflate(mContext, R.layout.dialog_loading,null);
        LinearLayout layout = view.findViewById(R.id.dialog_view);
        mLVCircleRing = view.findViewById(R.id.lvcr_loading);
        TextView loadingText = view.findViewById(R.id.loading_text);
        //显示文本
        loadingText.setText(msg);
        //创建自定义样式Dialog
        mDialog = new Dialog(mContext,R.style.LoadingDialog);
        mDialog.setContentView(layout,new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        //真正的显示
        mDialog.show();
        //记录标志
        isShow = true;
    }


    public void dismiss(){
        if(null != mDialog && isShow){
            mDialog.dismiss();
            mDialog = null;
            isShow = false;
        }
    }

}
