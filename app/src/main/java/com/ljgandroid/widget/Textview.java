package com.ljgandroid.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author:XWQ
 * Time   2019/1/10
 * Descrition: this is Textview
 */

public class Textview extends View
{
    public Textview(Context context)
    {
        this(context,null);
    }

    public Textview(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // get calculate mode of width and height
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        // get recommend width and height
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(dip2Px(100),dip2Px(100));
    }

    /*
    * converts dip to px
    */
    private int dip2Px(float dip)
    {
        return (int) (dip * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
       /* Paint mPaint=new Paint();
        mPaint.setAntiAlias(false);//设置画笔为无锯齿
        mPaint.setColor(Color.RED);//设置画笔颜色
        canvas.drawColor(Color.WHITE);//白色背景
        mPaint.setStrokeWidth((float) 3.0);//线宽
        mPaint.setStyle(Paint.Style.FILL);//空心效果
        canvas.drawCircle(50, 50, 30, mPaint);//绘制圆形*/
    }
}
