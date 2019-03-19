package com.example.module_slipt.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.module_slipt.R;
import com.example.module_slipt.bean.SliptMarquee;

import java.util.ArrayList;
import java.util.List;

/***
 * 直接拿小鸟的
 */
public class MarqueeView extends ViewFlipper {

    private Context mContext;
    private List<SliptMarquee> mSliptMarquees;
    private boolean isSetAnimDuration = false;
    private OnItemClickListener onItemClickListener;

    private int interval = 2000;//播放时间间隔
    private int animDuration = 500;
    private int textSize = 12;
    private boolean isImage = true;
    private int color = 0;

    public MarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;
        if (mSliptMarquees == null) {
            mSliptMarquees = new ArrayList<>();
        }
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MarqueeViewStyle, defStyleAttr, 0);
        interval = typedArray.getInteger(R.styleable.MarqueeViewStyle_mvInterval, interval);
        isSetAnimDuration = typedArray.hasValue(R.styleable.MarqueeViewStyle_mvAnimDuration);
        animDuration = typedArray.getInteger(R.styleable.MarqueeViewStyle_mvAnimDuration, animDuration);
//        if (typedArray.hasValue(R.styleable.MarqueeViewStyle_mvTextSize)) {
//            textSize = (int) typedArray.getDimension(R.styleable.MarqueeViewStyle_mvTextSize, textSize);
//            textSize = dp2px(textSize);
//        }

        typedArray.recycle();

        setFlipInterval(interval);
    }

    // 根据公告字符串启动轮播
    public void startWithText(final String notice) {
        if (TextUtils.isEmpty(notice)) return;
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
                startWithFixedWidth(notice, getWidth());
            }
        });
    }

    // 根据公告字符串列表启动轮播
    public void startWithList(List<SliptMarquee> sliptMarquees) {
        setSliptMarquees(sliptMarquees);
        start();
    }

    // 根据宽度和公告字符串启动轮播
    private void startWithFixedWidth(String notice, int width) {
        int noticeLength = notice.length();
        int dpW = px2dip(mContext, width);
        int limit = dpW / textSize;
        if (dpW == 0) {
            throw new RuntimeException("Please set MarqueeView width !");
        }
        List list = new ArrayList();
        if (noticeLength <= limit) {
            list.add(notice);
        } else {
            int size = noticeLength / limit + (noticeLength % limit != 0 ? 1 : 0);
            for (int i = 0; i < size; i++) {
                int startIndex = i * limit;
                int endIndex = ((i + 1) * limit >= noticeLength ? noticeLength : (i + 1) * limit);
                list.add(notice.substring(startIndex, endIndex));
            }
        }
        mSliptMarquees.addAll(list);
        start();
    }

    // 启动轮播
    public boolean start() {
        if (mSliptMarquees == null || mSliptMarquees.size() == 0) return false;
        removeAllViews();
        resetAnimation();

        for (int i = 0; i < mSliptMarquees.size(); i++) {
            final View view = createView(i);
            final int finalI = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(finalI, view);
                    }
                }
            });
            addView(view);
        }

        if (mSliptMarquees.size() > 1) {
            startFlipping();
        } else {
            stopFlipping();
        }
        return true;
    }

    private void resetAnimation() {
        clearAnimation();

        Animation animIn = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_in);
        if (isSetAnimDuration) animIn.setDuration(animDuration);
        setInAnimation(animIn);

        Animation animOut = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_out);
        if (isSetAnimDuration) animOut.setDuration(animDuration);
        setOutAnimation(animOut);
    }

    // 创建ViewFlipper下的View
    private View createView(int position) {
        SliptMarquee sliptMarquee = mSliptMarquees.get(position);
        View view = LayoutInflater.from(mContext).inflate(R.layout.slipt_marquee, null);
        TextView tvMarquee =  view.findViewById(R.id.tvMarquee);
        tvMarquee.setText(sliptMarquee.getDesc());

        if (color!=0){
            tvMarquee.setTextColor(color);
        }
        tvMarquee.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
        view.setTag(position);
        return view;
    }

    public int getPosition() {
        return (int) getCurrentView().getTag();
    }

    public List<SliptMarquee> getSliptMarquees() {
        return mSliptMarquees;
    }

    public void setSliptMarquees(List<SliptMarquee> sliptMarquees) {
        this.mSliptMarquees = sliptMarquees;
    }

    public void setText(int  color) {
        this.color = color;
    }

    public void setImage(boolean image) {
        this.isImage = image;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View textView);
    }

    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    public void useView() {

//        List<Marquee> marquees = new ArrayList<>();
//        List<Marquee> marquees1 = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            Marquee marquee = new Marquee();
//            marquee.setImgUrl(imgs[i]);
//            marquee.setTitle("我是图片轮播文字" + i);
//            marquees.add(marquee);
//            Marquee marquee1 = new Marquee();
//
//            marquee1.setTitle("我是没有文字的" + i);
//            marquees1.add(marquee1);
//        }
//        MarqueeView marqueeView = (MarqueeView) findViewById(R.id.marqueeView);
//        marqueeView.setImage(true);
//        marqueeView.startWithList(marquees);
//        MarqueeView marqueeView1 = (MarqueeView) findViewById(R.id.marqueeView1);
//        marqueeView1.setImage(false);
//        marqueeView1.startWithList(marquees1);
    }
}
