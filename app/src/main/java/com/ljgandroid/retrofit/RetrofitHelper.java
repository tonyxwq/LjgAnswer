package com.ljgandroid.retrofit;

import com.ljgandroid.Constants;

/**
 * Author:XWQ
 * Time   2019/1/4
 * Descrition: this is RetrofitHelper
 */

public class RetrofitHelper
{
    static GetRequest_Interface getRequest_interface;

    public static GetRequest_Interface getRequest_interface()
    {
        return getRequest_interface;
    }

    static
    {
        getRequest_interface = RetrofAPI.getApiService(GetRequest_Interface.class, Constants.Login);
    }
}
