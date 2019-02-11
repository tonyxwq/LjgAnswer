package com.ljgandroid.dagger.components;

import com.ljgandroid.SplashActivity;

import dagger.Component;

/**
 * Author:XWQ
 * Time   2019/1/15
 * Descrition: this is SplashComponent
 */

@Component
public interface SplashComponent
{
   void inject(SplashActivity splashActivity);
}
