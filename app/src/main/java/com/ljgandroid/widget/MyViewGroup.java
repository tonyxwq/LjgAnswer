package com.ljgandroid.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewGroup;
import android.content.Context;

import android.graphics.Canvas;

import android.graphics.Color;

import android.graphics.LinearGradient;

import android.graphics.Paint;

import android.graphics.Path;

import android.graphics.RectF;

import android.graphics.Shader;

import android.util.AttributeSet;

import android.view.View;
import android.widget.Scroller;

/**
 * Author:XWQ
 * Time   2019/1/10
 * Descrition: this is MyViewGroup
 */

public class MyViewGroup extends ViewGroup
{

    float mLastionMotionX = 0; //记录上次的触摸位置
    Scroller scroller;  //页面滑动类
    VelocityTracker velocityTracker; //手势速率跟踪  ，根据触摸位置计算每像素的移动速率
    int SNAP_VELOCITX = 600; //滑动的最小速率
    int currentScreen = 0;   //第一屏幕

    public MyViewGroup(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        scroller = new Scroller(context);
        // TODO Auto-generated constructor stub
    }


    //onLayout 在自定义控件中 作用将决定 子控件的摆放方式(如：水平，垂直)
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        // TODO Auto-generated method stub

        int totalWidth = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            View childView = getChildAt(i);
            if (childView.getVisibility() != View.GONE)
            {
                int childWidth = childView.getMeasuredWidth();
                int childHeight = childView.getMeasuredHeight();
                childView.layout(totalWidth, 0, totalWidth + childWidth, childHeight);
                totalWidth += childWidth;
            }
        }

    }

    //onMeasure 作用是用来测量每个子控件的宽高  最后决定自定义控件需要多少空间
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取子View的宽高
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        //计算ziView尺寸
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //计算自定义View的尺寸
        setMeasuredDimension(width, height);
    }

    //计算子View的宽度
    public int measureWidth(int widthMeasureSpec)
    {
        int result = 0;
        int measureMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        switch (measureMode)
        {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = width;
                break;
            default:
                break;
        }
        return result;
    }

    //计算子View的高度
    public int measureHeight(int heightMeasureSpec)
    {
        int result = 0;
        int measureMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        switch (measureMode)
        {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = height;
                break;
            default:
                break;
        }
        return result;
    }


    //onTouchEvent 主要是对 手势操作的处理(如：页面的滑动方向)
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        // TODO Auto-generated method stub
        float x = event.getX();
        if (velocityTracker == null)
        {
            velocityTracker = VelocityTracker.obtain();
        }
        //添加触摸对象MotionEvent ， 用于计算触摸速率
        velocityTracker.addMovement(event);
        //捕获 手势（DOWN,UP,MOVE,CANCEL）
        int action = event.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                mLastionMotionX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                int detalX = (int) (mLastionMotionX - x);
                //detalX>0 向左滑动  , detalX<0  向右滑动
                int scrollX = getScrollX();
                // 边界检查  防止页面滑动超出 显示区域
                if (detalX < 0 && scrollX + detalX < 0)
                { //detalX<0 向右滑动，如果不超出左屏幕显示边界 scrollX的绝对值永远是大于detalX滑动的手势距离
                    detalX = 0 - scrollX; //如果进入此条件，其实只有一种情况，那就是  scrollX = 0  的情况
                } else if (detalX > 0 && scrollX + detalX > (getChildCount() - 1) * getWidth())
                {// detalX>0 向左滑动, 如果不超出 右屏幕显示边界，scrollX的绝对值永远是 小于或等于ViewGroup的宽度的
                    detalX = (getChildCount() - 1) * getWidth() - scrollX;//如果进入此条件，也只有一种情况，那就是    scrollX=ViewGroup的宽度
                }
                //在原来离远点x坐标 基础上滑动detalX距离
                scrollBy(detalX, 0);
                mLastionMotionX = x;
                break;
            case MotionEvent.ACTION_UP:
                int velocityX = (int) velocityTracker.getXVelocity();
                if (velocityX > SNAP_VELOCITX && currentScreen > 0)
                {
                    snapToScreen(currentScreen - 1);
                } else if (velocityX < -SNAP_VELOCITX && currentScreen < (getChildCount() - 1))
                {
                    snapToScreen(currentScreen + 1);
                } else
                {
                    snapToDestination();
                }
                break;

            default:
                break;
        }

        return true;
    }

    @Override
    public void computeScroll()
    {
        // TODO Auto-generated method stub
        super.computeScroll();

        if (scroller.computeScrollOffset())
        {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }

    }

    private void snapToDestination()
    {
        // TODO Auto-generated method stub
        //当屏幕滑到getWidth()/2 的时候，此时总共有多少屏，即滑到第几屏
        int whichScreen = (getScrollX() + getWidth() / 2) / getWidth();
        snapToScreen(whichScreen);
    }

    //加上动画  缓慢的滑动
    public void snapToScreen(int whichScreen)
    {
        // 判断 屏幕是否已经滑动到 最后
        currentScreen = whichScreen;
        /*if( currentScreen > (getChildCount()-1)){
            currentScreen = getChildCount()-1;
        }*/
        // 需要滑动的距离    （ 开始 - 剩下 = 需要滑动的距离 ）
        //getScrollX() 滑出屏幕的第一页 开始到 左屏幕边界的距离
        int dx = currentScreen * getWidth() - getScrollX();
        //从getScrollX()坐标   滑动dx距离  要花 Math.abs(dx) * 2 ms
        scroller.startScroll(getScrollX(), 0, dx, 0, Math.abs(dx) * 2);
        //重新绘制界面
        postInvalidate();
    }

}
