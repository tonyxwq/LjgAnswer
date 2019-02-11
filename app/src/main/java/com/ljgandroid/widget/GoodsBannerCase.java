package com.ljgandroid.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ljgandroid.bean.GoodsBean;
import com.ljgandroid.tools.ScreenUtils;
import com.ljgandroid.tools.ToastUtils;
import com.ljganswer.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Author:XWQ
 * Time   2019/1/28
 * Descrition: this is GoodsBannerCase
 */

public class GoodsBannerCase extends LinearLayout
{

    String[] myList =
            {"http://c.hiphotos.baidu.com/image/h%3D300/sign=08d982d4b3096b639e1958503c328733/3801213fb80e7bec5678461a222eb9389a506bae.jpg",
                    "http://g.hiphotos.baidu.com/image/h%3D300/sign=f746d3fb6a09c93d18f208f7af3cf8bb/aa64034f78f0f7369192d5f40755b319eac413d2.jpg",
                    "http://d.hiphotos.baidu.com/image/h%3D300/sign=9d04ba5ca4773912db268361c8188675/9922720e0cf3d7ca10ccc397ff1fbe096b63a91d.jpg",
                    "http://g.hiphotos.baidu.com/image/h%3D300/sign=ea5d1597e6f81a4c3932eac9e72b6029/2e2eb9389b504fc2330c17e6e8dde71191ef6d86.jpg",
                    "http://g.hiphotos.baidu.com/image/h%3D300/sign=a1709aed96504fc2bd5fb605d5dce7f0/c8177f3e6709c93dfe911812923df8dcd00054f7.jpg"
            };

    int[] imagesUp = {R.mipmap.caishen, R.mipmap.bianpao, R.mipmap.denglong, R.mipmap.duilian,R.mipmap.meihua,R.mipmap.tangguo,R.mipmap.tongqian,R.mipmap.xueren,R.mipmap.yuanbao};
    int[] imagesDown = {R.mipmap.fu, R.mipmap.hongbao, R.mipmap.jinli, R.mipmap.orange,R.mipmap.shanzi,R.mipmap.tanghulu,R.mipmap.xuehua,R.mipmap.yanhua,R.mipmap.zhaocaimao};
    String[] namesUp = {"财神", "鞭炮", "灯笼", "对联","梅花","糖果","铜钱","雪人","元宝"};
    String[] namesDown = {"福", "红包", "锦鲤", "橘子树","扇子","糖葫芦","雪花","烟花","招财猫"};
    private ArrayList<GoodsBean> arrayList;

    List<GoodsBean> goodsBeanList = new ArrayList<>();

    /**
     * 速度追踪
     */
    private VelocityTracker mVelocityTracker;
    /**
     * 滑动的动画
     */
    private Animation mAnimation;

    private Scroller mScroller;
    //判定为拖动的最小移动像素数
    private int mTouchSlop;
    private int leftBorder;
    private int rigthBorder;
    private float xDown = 0;
    private float xMove = 0;
    private int baseNumber = 9;

    public GoodsBannerCase(Context context)
    {
        this(context, null);
    }

    public GoodsBannerCase(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        mScroller = new Scroller(context);
        arrayList = new ArrayList<>();
        ViewConfiguration configuration = ViewConfiguration.get(context);
        // 获取TouchSlop值
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
        setOrientation(LinearLayout.HORIZONTAL);
        Random random = new Random();
        for (int i = 0; i < baseNumber; i++)
        {
            GoodsBean user = new GoodsBean();
            GoodsBean.Up up = user.new Up();
            up.setName(namesUp[i]);
            up.setUrl(imagesUp[i]);
            user.setUp(up);

            GoodsBean.Down down = user.new Down();
            down.setName(namesDown[i]);
            down.setUrl(imagesDown[i]);
            user.setDown(down);

            goodsBeanList.add(user);
        }
        for (int i = 0; i < baseNumber; i++)
        {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.goodsbanner, null);
            MyTextview imageViewUp = view.findViewById(R.id.tv_scroll_up);
            MyTextview imageViewDown = view.findViewById(R.id.tv_scroll_down);
            int index = random.nextInt(5);
            //Glide.with(getContext()).load(myList[index]).into(imageViewUp);
            //Glide.with(getContext()).load(myList[index]).into(imageViewDown);
            TextView textViewUp=view.findViewById(R.id.tv_up_name);
            TextView textViewDown=view.findViewById(R.id.tv_down_name);
            GoodsBean goodsBean=goodsBeanList.get(i);
            imageViewUp.setBackgroundResource(goodsBean.getUp().getUrl());
            imageViewDown.setBackgroundResource(goodsBean.getDown().getUrl());
            textViewUp.setText(goodsBean.getUp().getName());
            textViewDown.setText(goodsBean.getDown().getName());
            imageViewDown.setTag(goodsBean.getDown().getName());
            imageViewUp.setTag(goodsBean.getUp().getName());
            addView(view);
            imageViewDown.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(getContext(), "" + v.getTag(), Toast.LENGTH_SHORT).show();
                }
            });
            imageViewUp.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(getContext(), "" + v.getTag(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        int totalWidth = 0;
        int totalHeight=0;
        for (int i = 0; i < count; i++)
        {
            View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            totalWidth += child.getMeasuredWidth();
            totalHeight=child.getMeasuredHeight();
        }
        int defaultHeight = getSuggestedMinimumHeight();
        int measureWidth = measureWidth(totalWidth, widthMeasureSpec);
        int measureHeight = measureHeight(defaultHeight, heightMeasureSpec);
        setMeasuredDimension(measureWidth, totalHeight);
        leftBorder = getChildAt(0).getLeft();
        rigthBorder = totalWidth;
        Log.d("mmp", "measureHeight:" + measureHeight);
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

    private float xInterDown;
    private float xInterMove;
    private int distance = 0;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                stopScroll();
                distance = 0;
                xInterDown = event.getX();
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
                Log.d("mmp", "父 onInterceptTouchEvent ACTION_DOWN" + super.onInterceptTouchEvent(event));
                break;
            case MotionEvent.ACTION_MOVE:
                xInterMove = event.getX();
                distance = (int) Math.abs(xInterDown - xInterMove);
                xInterDown = xInterMove;
                Log.d("mmp", "父 onInterceptTouchEvent ACTION_MOVE" + super.onInterceptTouchEvent(event));
                break;
            case MotionEvent.ACTION_UP:
                Log.d("mmp", "父 onInterceptTouchEvent ACTION_UP");
                break;
        }
        if (distance <= 0)
        {
            Log.d("mmp", "返回false");
            //默认返回false
            return super.onInterceptTouchEvent(event);

        } else
        {
            return true;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                Log.d("mmp", "父 onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("mmp", "父 onTouchEvent ACTION_MOVE");
                mVelocityTracker.addMovement(event);
                mVelocityTracker.computeCurrentVelocity(1000);//计算速度
                xMove = event.getX();
                int distance = (int) (xDown - xMove);
                //向右滑动
                if (distance < 0)
                {
                    int[] location = new int[2];
                    getChildAt(0).getLocationOnScreen(location);
                    int x = location[0];
                    if (x < 0)
                    {
                        scrollBy(distance, 0);

                    } else
                    {
                        scrollBy(x, 0);
                    }
                    //Log.d("mmp", "distance<0 x=:" + x);
                } else if (distance > 0)
                {
                    int[] location = new int[2];
                    getChildAt(baseNumber - 1).getLocationOnScreen(location);
                    int x = location[0];
                    int mx = (x - ScreenUtils.getWidth() + rigthBorder / baseNumber);
                    if (mx > 0)
                    {
                        scrollBy(distance, 0);

                    } else
                    {
                        scrollBy(mx, 0);
                    }
                }
                xDown = xMove;
                break;
            case MotionEvent.ACTION_UP:
                Log.d("mmp", "父 onTouchEvent ACTION_UP");
                if (mTouchSlop > 10)
                {
                    isStopAnimation = false;
                    sliding();
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
                    int distance = (int) ((1 - interpolatedTime) * speed/2);
                    if (isFirstComing)
                    {
                        startDistance = distance;
                        isFirstComing = false;

                    } else
                    {
                        endDistance = distance;
                        int minDistance = endDistance - startDistance;
                        //Log.d("mmp", "minDistance：" + minDistance);
                        //Log.d("mmp", "quick=:" + quick);
                        if (!isStopAnimation)
                        {
                            if (minDistance < 0)//向右滑动
                            {
                                int[] location = new int[2];
                                getChildAt(0).getLocationOnScreen(location);
                                int x = location[0];
                                if (x < 0)
                                {
                                    scrollBy(minDistance, 0);

                                } else
                                {
                                    scrollBy(x, 0);
                                }
                            } else if (minDistance > 0)
                            {
                                int[] location = new int[2];
                                getChildAt(baseNumber - 1).getLocationOnScreen(location);
                                int x = location[0];
                                int mx = (x - ScreenUtils.getWidth() + rigthBorder / baseNumber);
                                if (mx > 0)
                                {
                                    scrollBy(minDistance, 0);

                                } else
                                {
                                    scrollBy(mx, 0);
                                }
                            }
                        }
                        startDistance = endDistance;
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
