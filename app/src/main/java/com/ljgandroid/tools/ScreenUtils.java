package com.ljgandroid.tools;

import android.util.DisplayMetrics;

import com.ljgandroid.LjgApplication;

/**
 * Author:XWQ
 * Time   2019/1/8
 * Descrition: this is ScreenUtils
 */

public class ScreenUtils
{
    static DisplayMetrics dm;

    static
    {
        dm = LjgApplication.getInstance().getResources().getDisplayMetrics();
    }

    public static int getWidth()
    {
        int width = dm.widthPixels;
        return width;
    }

    public static int getHeight()
    {
        int heigth = dm.heightPixels;
        return heigth;
    }

}
