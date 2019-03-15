package com.example.module_main.ui.customview.fourday;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;

/***
 * viewgroup 中间 🈶4个view
 */
public class CustomerView extends ViewGroup {

    public CustomerView(Context context) {
        this(context,null);
    }

    public CustomerView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onDraw(Canvas canvas) {

    }
}
