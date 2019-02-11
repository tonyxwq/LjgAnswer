package com.ljgandroid;

import android.app.Application;

import com.ljgandroid.dagger.components.DaggerNetComponent;
import com.ljgandroid.dagger.components.NetComponent;
import com.ljgandroid.dagger.modules.NetModule;
import com.ljgandroid.tools.Utils;
import com.ljganswer.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Author:XWQ
 * Time   2019/1/2
 * Descrition: this is LjgApplication
 */

public class LjgApplication extends Application
{
    private static LjgApplication instance;
    private NetComponent netComponent;

    @Override
    public void onCreate()
    {
        super.onCreate();
        Utils.init(this);
        instance = this;
        initTypeFace();
        initNet();
    }

    public static LjgApplication getInstance()
    {
        return instance;
    }

    private void initTypeFace()
    {
        CalligraphyConfig calligraphyConfig = new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/PMingLiU.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
        CalligraphyConfig.initDefault(calligraphyConfig);
    }

    private void initNet()
    {
        netComponent = DaggerNetComponent.builder()
                .netModule(new NetModule())
                .build();
    }

    public NetComponent getNetComponent()
    {
        return netComponent;
    }
}
