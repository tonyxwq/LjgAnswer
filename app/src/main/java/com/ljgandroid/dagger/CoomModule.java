package com.ljgandroid.dagger;

import com.ljgandroid.view.ILoginView;

import dagger.Module;
import dagger.Provides;

/**
 * Author:XWQ
 * Time   2019/1/2
 * Descrition: this is CoomModule
 */

@Module
public class CoomModule
{
    ILoginView iLoginView;

    public CoomModule(ILoginView iLoginView)
    {
        this.iLoginView = iLoginView;
    }

    @Provides
    public ILoginView getRestaurant()
    {
        return iLoginView;
    }
}
