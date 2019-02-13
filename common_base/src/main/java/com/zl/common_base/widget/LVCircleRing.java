package com.zl.common_base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zl.common_base.R;

/***
 * 自定义加载框View
 * 1.画圆 + 画弧，以自定义view的左上角为坐标原点
 * 2.TODO 待补充动画
 */
public class LVCircleRing extends View {

    private float startAngle = 0f;//起始角度
    private int mStrokeWidth = 4;//空心线宽
    private int mPadding = 5;//内边距
    private int mWidth;//宽度
    private Paint mPaint;//画笔
    private int bgColor = Color.argb(100, 245, 245, 245);//加载框背景色
    private int barColor = Color.argb(100, 245, 245, 245);//加载框动画色

    public LVCircleRing(Context context) {
        this(context,null);
    }

    public LVCircleRing(Context context,  @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LVCircleRing(Context context,  @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /** 第一个参数是属性集合  第二个参数是定义在styleable中的名字 */
    private void init(Context context,  @Nullable AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LVCircleRing);
        bgColor = a.getColor(R.styleable.LVCircleRing_bgColor,bgColor);
        barColor = a.getColor(R.styleable.LVCircleRing_barColor,barColor);
        a.recycle();
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);//设置空心线宽
    }


    /** 简单处理 以两者中较小的等高等宽 */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(getMeasuredWidth() > getMeasuredHeight()){
            mWidth = getMeasuredHeight();
        }else
            mWidth = getMeasuredWidth();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(bgColor);
        canvas.drawCircle(mWidth / 2,mWidth / 2,mWidth / 2 - mPadding,mPaint);

        mPaint.setColor(barColor);
        RectF oval = new RectF(0,0,mWidth,mWidth);
        canvas.drawArc(oval,startAngle,100,false,mPaint);
    }
}
