package com.ljgandroid.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.ljganswer.R;

/**
 * Author:XWQ
 * Time   2019/1/18
 * Descrition: this is GoodsBanner
 */

public class GoodsBannertttt extends LinearLayout
{
    //public abstract void clickItem(int position);
    private Scroller mScroller;
    private int mTouchSlop;//判定为拖动的最小移动像素数

    private float mScrollerTotallDistance = 0;
    private int mTotallDistance = 0;

    private int leftBorde;
    private int rigthBorde;
    private int Move;


    private int lastIndex=0;

    /**
     * 速度追踪
     */
    private VelocityTracker mVelocityTracker;
    /**
     * 滑动的动画
     */
    private Animation mAnimation;

    public GoodsBannertttt(Context context)
    {
        this(context, null);
    }

    public GoodsBannertttt(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        // 第一步，创建Scroller的实例
        mScroller = new Scroller(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        // 获取TouchSlop值
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
        setOrientation(LinearLayout.HORIZONTAL);
        for (int i = 0; i < 100; i++)
        {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.goodsbanner, null);
            TextView textView = view.findViewById(R.id.tv_scroll_up);
            textView.setText((i + 1) + "");
            addView(view);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        int totalWidth = 0;
        for (int i = 0; i < count; i++)
        {
            View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            totalWidth += child.getMeasuredWidth();
        }
        mTotallDistance = totalWidth;
        int defaultHeight = getSuggestedMinimumHeight();
        int measureWidth = measureWidth(totalWidth, widthMeasureSpec);
        int measureHeight = measureHeight(defaultHeight, heightMeasureSpec);
        setMeasuredDimension(measureWidth, measureHeight);
        leftBorde = getChildAt(0).getLeft();
        rigthBorde = totalWidth;
        Log.d("mmp", "rigthBorde:" + rigthBorde);
    }

    private int measureWidth(int defaultWidth, int measureSpec)
    {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int whdth = defaultWidth;
        switch (mode)
        {
            case MeasureSpec.EXACTLY:
                whdth = size;
                break;
            case MeasureSpec.AT_MOST:
                whdth = size;
                break;
        }

        return whdth > defaultWidth ? whdth : defaultWidth;
    }

    private int measureHeight(int defaultHeight, int measureSpec)
    {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int height = defaultHeight;
        switch (mode)
        {
            case MeasureSpec.EXACTLY:
                height = size;
                break;
            case MeasureSpec.AT_MOST:
                height = size;
                break;
        }
        return height;
    }


    float xDown  = 0;
    float xMove  = 0;
    float moveDistance;

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                stopScroll();
                xDown = event.getRawX();
                //取得velocityTracker实例
                if (mVelocityTracker == null)
                {
                    mVelocityTracker = VelocityTracker.obtain();
                } else
                {
                    mVelocityTracker.clear();
                }
                mVelocityTracker.addMovement(event);
                break;
            case MotionEvent.ACTION_MOVE:

                mVelocityTracker.addMovement(event);
                mVelocityTracker.computeCurrentVelocity(1000);//计算速度
                xMove = event.getRawX();
                moveDistance = xDown - xMove;

                if (moveDistance > 0)
                {
                    if (getScrollX() + 1080 + moveDistance < mTotallDistance)
                    {
                        scrollBy((int) moveDistance, 0);
                        mScrollerTotallDistance += moveDistance;
                        Log.d("mmp", "moveDistance:" + moveDistance);
                    }
                } else if (moveDistance < 0)
                {
                    if (getScrollX() + moveDistance > 0)
                    {
                        scrollBy((int) moveDistance, 0);
                    }
                }
                xDown = xMove;
                break;
            case MotionEvent.ACTION_UP:
                //Log.d("mmp", "moveDistance = " + moveDistance);
                //Log.d("mmp", "mTouchSlop=" + mTouchSlop);
                if (Math.abs(moveDistance) > 0.5)
                {
                    //手势滑动之后继续滚动
                    sliding();
                    isStopAnimation = false;
                } else
                {
                    //过滤点击不小心滑动
                    //如果是点击操作
                    //do something
                }

                break;
        }
        return true;
    }

    private int startDistance = 0;
    private int endDistance = 0;
    private boolean isFirstComing = true;
    private boolean isStopAnimation = false;

    /**
     * 滑动操作后的惯性滑动。
     * 利用animation实现
     */
    private void sliding()
    {
        if (mAnimation == null)
        {
            mAnimation = new Animation()
            {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t)
                {
                    float speed = mVelocityTracker.getXVelocity();
                    //interpolatedTime是当前的时间插值， 从0-1减速变化
                    //所以(1 - interpolatedTime)就是从1-0减速变化，
                    //而(1 - interpolatedTime) * speed就是将当前速度乘以插值，速度也会跟着从speed-0减速变化，
                    //将(1 - interpolatedTime) * speed)用于重绘，就可以实现平滑的滚动
                    //Log.d("mmp", "cur speed = " + String.valueOf((1 - interpolatedTime) * speed));
                    int distance = (int) ((1 - interpolatedTime) * speed);

                    if (isFirstComing)
                    {
                        startDistance = distance;
                        isFirstComing = false;

                    } else
                    {
                        endDistance = distance;
                        int minDistance = endDistance - startDistance;
                        Move += minDistance;
                        startDistance = endDistance;
                        if (!isStopAnimation)
                        {
                            if (distance > 0)//向右滑动
                            {
                                scrollBy(-(Math.abs(minDistance)), 0);

                            } else
                            {
                                int x = Move + (int) mScrollerTotallDistance + 1080;
                                //Log.d("mmp", "x:" + x);
                                if (x < rigthBorde)
                                {
                                    lastIndex=x;
                                    scrollBy(Math.abs(minDistance), 0);
                                } else
                                {
                                    mAnimation.cancel();
                                    int mx=36000-lastIndex;
                                    //scrollBy(Math.abs(mx), 0);
                                }
                            }
                        }
                    }
                }
            };
            mAnimation.setInterpolator(new DecelerateInterpolator());//设置一个减速插值器
        }
        mAnimation.setDuration(2000);
        startAnimation(mAnimation);
    }

    /**
     * 停止滑动
     */
    private void stopScroll()
    {
        startDistance = 0;
        endDistance = 0;
        isFirstComing = true;
        isStopAnimation = true;
        if (mAnimation != null && !mAnimation.hasEnded())
        {
            mAnimation.cancel();
            clearAnimation();
        }
    }

    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if (mVelocityTracker != null)
        {//要记得回收
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    @Override
    public void computeScroll()
    {
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (mScroller.computeScrollOffset())
        {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
