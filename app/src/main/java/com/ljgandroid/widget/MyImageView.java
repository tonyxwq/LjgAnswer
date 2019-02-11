package com.ljgandroid.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Author:XWQ
 * Time   2019/1/30
 * Descrition: this is MyImageView
 */

public class MyImageView extends ImageView
{
    public MyImageView(Context context)
    {
        this(context, null);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    private float xInterDown;
    private float xInterMove;
    private int distance = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                xInterDown = event.getX();
                Log.d("mmp", "子view onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                xInterMove = event.getX();
                distance = (int) Math.abs(xInterDown - xInterMove);
                xInterDown = xInterMove;
                Log.d("mmp", "子view onTouchEvent ACTION_MOVE" + distance);
                break;
            case MotionEvent.ACTION_UP:
                Log.d("mmp", "子view onTouchEvent ACTION_UP" + distance);
                break;
        }
        if (distance <= 1)
        {
            return super.onTouchEvent(event);

        } else
        {
            return false;
        }
    }
}
