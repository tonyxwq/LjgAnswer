package com.ljgandroid.model;

/**
 * Author:XWQ
 * Time   2018/12/28
 * Descrition: this is ILoginModel
 */

/**
 * 登录数据模型
 */
public interface ILoginModel extends IBaseModel
{

    void Login(String userName, String Password, ILoginCallback mILoginCallback);

    void Login(String userName, String Password, String[] list,String test_id,ILoginCallback mILoginCallback);

    //登录回调接口 Success
    interface ILoginCallback
    {
        void success(String msgSuccess);
        void error(String msgError);
    }

}
