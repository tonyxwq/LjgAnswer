package com.ljgandroid.tools;

import android.content.Context;

/**
 * Author:XWQ
 * Time   2019/1/4
 * Descrition: this is Utils
 */

public class Utils
{
    private static Context context;

    public Utils()
    {
        throw new UnsupportedOperationException("no! no! no!");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context)
    {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext()
    {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }

}
