package com.ljgandroid;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Author:XWQ
 * Time   2018/12/28
 * Descrition: this is BaseActivity
 */

public abstract class BaseActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // 添加Activity到堆栈
        AtyContainer.getInstance().addActivity(this);
        setContentView(getContentView());
        ButterKnife.bind(this);

        initWidget();
        initData();
    }

    protected abstract int getContentView();

    protected void initWidget()
    {
    }

    protected void initData()
    {
    }

    public static class AtyContainer
    {
        private AtyContainer()
        {
        }

        private static AtyContainer instance = new AtyContainer();
        private static List<Activity> activityStack = new ArrayList<Activity>();

        public static AtyContainer getInstance()
        {
            return instance;
        }

        public void addActivity(Activity aty)
        {
            activityStack.add(aty);
        }

        public void removeActivity(Activity aty)
        {
            activityStack.remove(aty);
        }

        /**
         * 结束所有Activity
         */
        public void finishAllActivity()
        {
            for (int i = 0, size = activityStack.size(); i < size; i++)
            {
                if (null != activityStack.get(i))
                {
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        }

    }

}
