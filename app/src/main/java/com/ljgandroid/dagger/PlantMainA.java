package com.ljgandroid.dagger;

import com.ljgandroid.MainActivity;
import com.ljgandroid.presenter.LoginPresenter;
import com.ljgandroid.view.ILoginView;

import dagger.Component;

/**
 * Author:XWQ
 * Time   2019/1/2
 * Descrition: this is PlantMainA
 */

@Component(modules = CoomModule.class)
public interface PlantMainA
{
    void inject(LoginPresenter loginPresenter);
    void inject (MainActivity mainActivity);
}
