package com.example.module_main.ui.customview.firstday;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/***
 * 仿时钟效果
 */
public class TimeView  extends View {

    private Paint mPaint;
    private int mRadius ;
    private int mWidth;
    private int mHeight;


    public TimeView(Context context) {
        this(context,null);
    }

    public TimeView(Context context,@Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TimeView(Context context,  @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mHeight = getHeight();
        mRadius = mWidth / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制矩形
        Rect rect = new Rect(0,0,mWidth,mHeight);
        canvas.drawRect(rect,mPaint);

        //绘制圆
        mPaint.setColor(getResources().getColor(com.zl.common_base.R.color.white));
        canvas.drawCircle(mWidth / 2 ,mHeight / 2,mRadius,mPaint);
        //绘制线


        //绘制文字

    }
}
