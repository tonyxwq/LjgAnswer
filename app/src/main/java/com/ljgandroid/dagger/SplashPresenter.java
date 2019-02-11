package com.ljgandroid.dagger;

import android.util.Log;

import com.ljgandroid.LjgApplication;
import com.ljgandroid.bean.SplashEntity;
import com.ljgandroid.tools.NetUtil;
import com.ljgandroid.tools.OkHttpImageDownloader;
import com.ljgandroid.tools.TimeUtil;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Author:XWQ
 * Time   2019/1/15
 * Descrition: this is SplashPresenter
 */

public class SplashPresenter
{
    @Inject
    public SplashPresenter()
    {
    }

    public void getSplash(String deviceId)
    {
        String client = "android";
        String version = "1.3.0";
        Long time = TimeUtil.getCurrentSeconds();
        LjgApplication.getInstance().getNetComponent().getApiService().getSplash(client, version, time, deviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<SplashEntity>()
                {
                    @Override
                    public void accept(SplashEntity splashEntity) throws Exception
                    {
                        if (NetUtil.isWifi(LjgApplication.getInstance().getApplicationContext()))
                        {
                            if (splashEntity != null)
                            {
                                List<String> imgs = splashEntity.getImages();
                                for (String url : imgs)
                                {
                                    OkHttpImageDownloader.download(url);
                                }
                            }
                        } else
                        {
                            Log.d("mmp", "不是WIFI环境,就不去下载图片了");
                        }
                    }
                });
    }
}
