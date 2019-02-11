package com.ljgandroid.retrofit;

import com.ljgandroid.RetrofitUtils;

import retrofit2.Retrofit;

/**
 * Author:XWQ
 * Time   2019/1/4
 * Descrition: this is RetrofAPI
 */

public class RetrofAPI
{
    public static <T> T getApiService(Class<T> tClass, String baseUrl)
    {
        Retrofit retrofit = RetrofitUtils.getRetrofitBuilder(baseUrl).build();
        return retrofit.create(tClass);
    }
}
