package com.ljgandroid.widget;

import android.content.Context;
import android.graphics.Rect;
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
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ljganswer.R;

import java.util.Random;

/**
 * Author:XWQ
 * Time   2019/1/18
 * Descrition: this is GoodsBanner
 */

public class GoodsBanner extends LinearLayout
{
    String[] myList =
            {"http://c.hiphotos.baidu.com/image/h%3D300/sign=08d982d4b3096b639e1958503c328733/3801213fb80e7bec5678461a222eb9389a506bae.jpg",
                    "http://g.hiphotos.baidu.com/image/h%3D300/sign=f746d3fb6a09c93d18f208f7af3cf8bb/aa64034f78f0f7369192d5f40755b319eac413d2.jpg",
                    "http://d.hiphotos.baidu.com/image/h%3D300/sign=9d04ba5ca4773912db268361c8188675/9922720e0cf3d7ca10ccc397ff1fbe096b63a91d.jpg",
                    "http://g.hiphotos.baidu.com/image/h%3D300/sign=ea5d1597e6f81a4c3932eac9e72b6029/2e2eb9389b504fc2330c17e6e8dde71191ef6d86.jpg",
                    "http://g.hiphotos.baidu.com/image/h%3D300/sign=a1709aed96504fc2bd5fb605d5dce7f0/c8177f3e6709c93dfe911812923df8dcd00054f7.jpg"
            };

    //public abstract void clickItem(int position);
    private Scroller mScroller;
    private int mTouchSlop;//判定为拖动的最小移动像素数

    private float mScrollerTotallDistance = 0;
    private int mTotallDistance = 0;

    private int leftBorde;
    private int rigthBorde;
    private int Move;
    private int tempMove;

    private int rightLastIndex = 0;
    private int leftLastIndex = 0;

    /**
     * 速度追踪
     */
    private VelocityTracker mVelocityTracker;
    /**
     * 滑动的动画
     */
    private Animation mAnimation;

    public GoodsBanner(Context context)
    {
        this(context, null);
    }

    public GoodsBanner(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        // 第一步，创建Scroller的实例
        mScroller = new Scroller(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        // 获取TouchSlop值
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
        setOrientation(LinearLayout.HORIZONTAL);
        Random random = new Random();
        for (int i = 0; i < 10; i++)
        {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.goodsbanner, null);
            ImageView imageView = view.findViewById(R.id.tv_scroll_up);
            int index = random.nextInt(5);
            Glide.with(getContext()).load(myList[index]).into(imageView);
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

    float xDown = 0;
    float xMove = 0;
    float moveDistance = 0;
    int xMoveTotallDistance = 0;
    int tempXMoveTotallDistance;
    boolean mScrollLeft = false;
    boolean mScrollRight = false;
    int Value = 0;
    float dif;

    int gg;

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {

            case MotionEvent.ACTION_DOWN:
                stopScroll();
                xDown = event.getX();
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
                xMove = event.getX();
                dif = xDown - xMove;
                if (mScrollLeft && !mScrollRight || !mScrollLeft && mScrollRight)
                {
                    moveDistance = xDown - xMove;
                    xMoveTotallDistance = xMoveTotallDistance + (int) moveDistance;
                }
                //判断手指滑动方向
                if (dif > 0.1)//向左滑动
                {
                    //Log.d("mmp","left-getScrollX():"+getScrollX()+"=====moveDistance=======:"+moveDistance);

                    if (getScrollX() + 1080 + moveDistance <= mTotallDistance)
                    {
                        mScrollLeft = true;
                        mScrollRight = false;
                        scrollBy((int) moveDistance, 0);

                    } else
                    {
                        mScrollLeft = false;
                    }

                } else if (dif < -0.1)
                {
                    int[] location = new int[2];
                    getChildAt(0).getLocationOnScreen(location);
                    int x = location[0];
                    if (x < 0)
                    {
                        scrollBy((int) moveDistance, 0);
                        mScrollRight = true;
                        mScrollLeft = false;

                    } else
                    {
                        //Log.d("mmp", "============" + x + "=====moveDistance======" + moveDistance);
                        scrollBy(x,0);
                        mScrollRight = false;
                        xMoveTotallDistance+=x;
                    }
                }
                xDown = xMove;
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs(dif) > 0.01)
                {
                    if (dif > 0)//向左滑动
                    {
                        if (mScrollLeft)//向左滑动并且没有滚动到最右边边界
                        {
                            //手势滑动之后继续滚动
                            sliding();
                            //Log.d("mmp","向左滑动");
                        }
                    } else if (dif < 0)//向右滚动
                    {
                        if (mScrollRight)//向右滑动并且没有滚动到最左边边界
                        {
                            //手势滑动之后继续滚动
                            sliding();
                        }
                    }
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
                            if (distance >= 0)//向右滑动
                            {
                                int xl = xMoveTotallDistance + Move;
                                if (xl > 0)
                                {
                                    tempMove = Move;
                                    leftLastIndex = xl;
                                    scrollBy(-(Math.abs(minDistance)), 0);

                                } else
                                {
                                    mAnimation.cancel();
                                    int difference = 0 - leftLastIndex;
                                    xMoveTotallDistance += difference;
                                    moveDistance += difference;
                                    scrollBy(difference, 0);
                                    Move = tempMove;
                                }
                            } else
                            {
                                int xr = xMoveTotallDistance + Move + 1080;
                                if (xr < rigthBorde)
                                {
                                    tempMove = Move;
                                    rightLastIndex = xr;
                                    scrollBy(Math.abs(minDistance), 0);

                                } else
                                {
                                    mAnimation.cancel();
                                    int difference = rigthBorde - rightLastIndex;
                                    xMoveTotallDistance += difference;
                                    moveDistance += difference;
                                    scrollBy(difference, 0);
                                    Move = tempMove;
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
