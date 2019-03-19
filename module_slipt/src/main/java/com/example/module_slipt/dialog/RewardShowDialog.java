package com.example.module_slipt.dialog;

import android.app.Dialog;
import android.content.Context;
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
 * 拆红包 奖励弹出框
 */

public class RewardShowDialog {

    private Context context;
    private Dialog dialog;
    private Display display;
    private ImageView ad_pic;
    private ImageView ad_close;
    private TextView toGet;
    private RewardBean mRewardBean;

    public RewardShowDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context
                .WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public RewardShowDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.slipt_dialog_reward_show, null);

        // 获取自定义Dialog布局中的控件
        ad_pic =  view.findViewById(R.id.ad_pic);
        ad_close = view.findViewById(R.id.ad_close);
        toGet = view.findViewById(R.id.toGet);

        //关闭
        ad_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //立即领取
        toGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("heihei","领取成功，弹出其他的框");

            }
        });

        //广告
        ad_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("heihei","广告");
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

    public RewardShowDialog  setAdBean(RewardBean rewardBean){
        mRewardBean = rewardBean;
        return this;
    }


    public RewardShowDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }


    public RewardShowDialog setCanceledOnTouchOutside(boolean b) {
        dialog.setCanceledOnTouchOutside(b);
        return this;
    }



    public RewardShowDialog setPositionButton(String text, final View.OnClickListener listener) {

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



    public RewardShowDialog setImageViewListener( final View.OnClickListener listener) {

        ad_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                }
                dialog.dismiss();
            }
        });

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