package com.ljgandroid.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.ljgandroid.LjgApplication;
import com.ljgandroid.bean.NavigationBean;
import com.ljgandroid.tools.ScreenUtils;
import com.ljganswer.R;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.badgeview.QBadgeView;

/**
 * Author:XWQ
 * Time   2019/1/8
 * Descrition: this is BottomTabView
 */

public class BottomTabView extends ViewGroup implements View.OnTouchListener
{
    public interface clickTouchListener
    {
        void Click(int index);
    }
    private static clickTouchListener clickTouchListener;
    public static void setClickTouchListener(clickTouchListener clickTouchListeners)
    {
        clickTouchListener = clickTouchListeners;
    }
    private List<NavigationBean> list;
    private int circleBaseRight=15;
    private int circleBaseBottom=20;
    private int interpolator=1;
    private int dynamic=0;
    private int base = 4;

    public BottomTabView(Context context)
    {
        this(context, null);
    }

    public BottomTabView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        this.setOnTouchListener(this);
        list = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++)
        {
            View view = getChildAt(i);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                int str = determineLocation((int) x);
                if (str != -1)
                {
                    clickTouchListener.Click(str);
                }
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        //去锯齿
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        //设置填丛风格后进行绘制
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        for (int i = 0; i < list.size(); i++)
        {
            NavigationBean navigationBean = list.get(i);
            if (navigationBean.isVisibile())
            {
                String text = navigationBean.getMsg();
                int Row = (((2 * i) + 1) * navigationBean.getCell() + (i + 1) * navigationBean.getWidth()) ;
                //Log.d("mmp","cell:"+navigationBean.getCell());
                //Log.d("mmp","width:"+navigationBean.getWidth());
                //Log.d("mmp","Row:"+Row);

                //消息小红点的文本最多三个长度 根据长度来设置小红点宽高
                int length=text.length();
                switch (length)
                {
                    case 0:
                        break;
                    case 1:
                        dynamic=30;
                        circleBaseRight=15;
                        Canvas(Row,paint,canvas,text,dynamic);
                        break;
                    case 2:
                        dynamic=35;
                        circleBaseRight=20;
                        Canvas(Row,paint,canvas,text,dynamic);
                        break;
                    case 3:
                        dynamic=25;
                        circleBaseRight=25;
                        text="99+";
                        Canvas(Row,paint,canvas,text,dynamic);
                        break;
                }
            }
        }
    }

    private void Canvas(int Row,Paint paint,Canvas canvas,String text,int dynamic)
    {
        //RectF re2 = new RectF(dip2Px(Row), dip2Px(5), dip2Px(Row + circleBaseRight), dip2Px(circleBaseBottom));
        RectF re2 = new RectF(Row, dip2Px(5), Row + dip2Px(circleBaseRight) ,dip2Px(circleBaseBottom) );
        paint.setColor(Color.RED);
        paint.setStrokeWidth(dip2Px(12));//线宽
        paint.setTextSize(dip2Px(10));
        canvas.drawRoundRect(re2, dip2Px(dynamic), dip2Px(dynamic), paint);
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        int width = rect.width();//文本的宽度
        //int left = dip2Px(Row) + ((dip2Px(Row + circleBaseRight) - dip2Px(Row)) - width) / 2-interpolator;
        int left = Row + (  dip2Px(circleBaseRight)- width) / 2-interpolator;
        paint.setColor(Color.WHITE);
        canvas.drawText(text, left, dip2Px(16), paint);
    }

    private int dip2Px(float dip)
    {
        return (int) (dip * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        list.clear();
        //得到屏幕宽度
        int screen = ScreenUtils.getWidth();
        int screenHeight = getHeight();
        int childCount = getChildCount();
        for (int i = 0; i < getChildCount(); i++)
        {
            View childView = getChildAt(i);
            int width = childView.getMeasuredWidth();
            int height = childView.getMeasuredHeight();
            int heights = Math.abs(screenHeight - height) / 2;
            int over = screen - (childCount * width);
            int count = childCount * 2;
            int cell = over / count;
            int left = (cell * (2 * (i + 1) - 1) + i * width);
            int right = (cell * (2 * (i + 1) - 1) + width * (i + 1));
            childView.layout(left, heights + base, right, height + heights + base);
            int distance = width * (i + 1) + ((2 * i) + 2) * cell;
            NavigationBean navigationBean = new NavigationBean();
            if (i == 0)
            {
                navigationBean.setStart(0);

            } else
            {
                navigationBean.setStart(list.get(i - 1).getEnd());
            }
            if (i == 0 || i == 2)
            {
                navigationBean.setVisibile(true);
                switch (i)
                {
                    case 0:
                        navigationBean.setMsg("9");
                        break;
                    case 2:
                        navigationBean.setMsg("100");
                        break;
                }
            } else
            {
                navigationBean.setVisibile(false);
                navigationBean.setMsg("88");
            }
            navigationBean.setEnd(distance);
            navigationBean.setCell(cell);
            navigationBean.setWidth(width);
            navigationBean.setIndex(i);
            list.add(navigationBean);
        }

        Log.d("mmp", "onLayout:执行了");
    }

    private int determineLocation(int location)
    {
        int index = -1;
        for (int i = 0; i < list.size(); i++)
        {
            int start = list.get(i).getStart();
            int end = list.get(i).getEnd();
            if (location > start && location < end)
            {
                index = i;
                break;
            }
        }
        return index;
    }
}
