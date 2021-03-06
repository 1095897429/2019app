package com.example.module_slipt.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_slipt.R;
import com.example.module_slipt.bean.RewardBean;


/***
 * 拆红包 任务[邀请任务]弹出框
 */

public class SliptTaskShowDialog {

    private Context context;
    private Dialog dialog;
    private Display display;
    private TextView ad_remark;
    private TextView ad_see_more;
    private TextView ad_go_complete;
    private TextView ad_coin;
    private RewardBean mRewardBean;

    public SliptTaskShowDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context
                .WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public SliptTaskShowDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.slipt_dialog_task_show, null);

        // 获取自定义Dialog布局中的控件
        ad_remark = view.findViewById(R.id.ad_remark);
        ad_see_more = view.findViewById(R.id.ad_see_more);
        ad_go_complete = view.findViewById(R.id.ad_go_complete);
        ad_coin = view.findViewById(R.id.ad_coin);
        //设置字体
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"fonts/din-bold_.otf");
        ad_coin.setTypeface(typeface);

        //再看看
        ad_see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Log.d("heihei","再看看");
            }
        });

        //前往完成
        ad_go_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("heihei","前往完成");
            }
        });


        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.CustomerDialog);
        dialog.setContentView(view);

        // 调整dialog背景大小
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (display.getWidth() * 0.7);

        return this;
    }

    public SliptTaskShowDialog setRemark(String remark){
        ad_remark.setText(remark);
        return this;
    }


    public SliptTaskShowDialog setAdBean(RewardBean rewardBean){
        mRewardBean = rewardBean;
        return this;
    }


    public SliptTaskShowDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }


    public SliptTaskShowDialog setCanceledOnTouchOutside(boolean b) {
        dialog.setCanceledOnTouchOutside(b);
        return this;
    }



    public SliptTaskShowDialog setPositionButton(String text, final View.OnClickListener listener) {

//        mTextView.setText(text);
//        /** 只有独立的按钮 */
//        mTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener != null) {
//                    listener.onClick(v);
//                }
//                dialog.dismiss();
//            }
//        });

        return this;
    }



    public SliptTaskShowDialog setImageViewListener(final View.OnClickListener listener) {

//        ad_pic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener != null) {
//                    listener.onClick(v);
//                }
//                dialog.dismiss();
//            }
//        });

        return this;
    }



    private void setLayout() {
        if(mRewardBean != null){
//            Glide.with(context)
//                    .load(mRewardBean.getImgUrl())
//                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                    .placeholder(R.mipmap.dialog_ad)
//                    .centerCrop()
//                    .crossFade()
//                    .into(mImageView);
        }


    }

    public void show() {
        setLayout();
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }


}