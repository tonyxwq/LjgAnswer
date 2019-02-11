package com.ljgandroid.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Author:XWQ
 * Time   2019/1/29
 * Descrition: this is MyTextview
 */

public class MyTextview extends ImageView
{
    public MyTextview(Context context)
    {
        this(context, null);
    }

    public MyTextview(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    private float xInterDown;
    private float xInterMove;
    private int distance = -1;
    private boolean isClick=true;

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                distance=0;
                xInterDown = event.getX();
                Log.d("mmp", "子view onTouchEvent ACTION_DOWN" +super.onTouchEvent(event));
                break;
            case MotionEvent.ACTION_MOVE:
                xInterMove = event.getX();
                distance = (int) Math.abs(xInterDown - xInterMove);
                xInterDown = xInterMove;
                Log.d("mmp", "子view onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("mmp", "子view onTouchEvent ACTION_UP"+distance);
                break;
        }

        if(distance<=1)
        {
            return super.onTouchEvent(event);

        }else
        {
            return false;
        }
    }
}
