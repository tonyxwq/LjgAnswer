package com.ljgandroid.model.impl;

import android.util.Log;

import com.ljgandroid.bean.AnswerBean;
import com.ljgandroid.bean.JavaBean;
import com.ljgandroid.model.ILoginModel;
import com.ljgandroid.retrofit.RetrofitHelper;
import com.ljgandroid.rxjava.observe.DefaultObserver;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author:XWQ
 * Time   2018/12/28
 * Descrition: this is ILoginModelImp
 */

public class ILoginModelImp implements ILoginModel
{
    @Inject
    public ILoginModelImp()
    {
    }

    @Override
    public void Login(String userName, String Phone, String[] list, String test_id, ILoginCallback mILoginCallback)
    {
        RetrofitHelper.getRequest_interface().getSplash(test_id, list[0], list[1], list[2], list[3], list[4], list[5], list[6], list[7], list[8], list[9], list[10], list[11], list[12], list[13], list[14], userName, Phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<AnswerBean>()
                {
                    @Override
                    public void onSuccess(AnswerBean response)
                    {
                        if (response != null)
                        {

                        }
                    }

                    @Override
                    public void onError(String error)
                    {

                    }
                });
    }

    @Override
    public void Login(String userName, String Password, final ILoginCallback mILoginCallback)
    {
        RetrofitHelper.getRequest_interface().getCall()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<JavaBean>()
                {
                    @Override
                    public void onSuccess(JavaBean response)
                    {
                        if (response != null)
                        {
                            mILoginCallback.success(response.getResult().getItems().toString());
                        }
                    }

                    @Override
                    public void onError(String error)
                    {
                        mILoginCallback.error(error);
                    }
                });
    }
}
