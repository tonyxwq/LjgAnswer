package com.ljgandroid.presenter;

import com.ljgandroid.model.ILoginModel;
import com.ljgandroid.model.impl.ILoginModelImp;
import com.ljgandroid.view.ILoginView;

import javax.inject.Inject;

/**
 * Author:XWQ
 * Time   2018/12/28
 * Descrition: this is LoginPresenter
 */

public class LoginPresenter
{
    @Inject
    ILoginView iLoginView;

    @Inject
    ILoginModelImp iLoginModelImp;

    @Inject
    public LoginPresenter()
    {
    }

    public void Login(String userName, String Phone,String[] list,String test_id)
    {
        iLoginModelImp.Login(userName, Phone, list,test_id,new ILoginModel.ILoginCallback()
        {
            @Override
            public void success(String msgSuccess)
            {
                iLoginView.showMessage(msgSuccess);
            }

            @Override
            public void error(String msgError)
            {
                iLoginView.showMessage(msgError);
            }
        });
    }

}
