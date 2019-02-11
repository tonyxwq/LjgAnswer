package com.ljgandroid.retrofit.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author:XWQ
 * Time   2019/1/4
 * Descrition: this is HttpHeaderInterceptor
 */

public class HttpHeaderInterceptor implements Interceptor
{
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException
    {
        //  配置请求头
        String accessToken = "token";
        String tokenType = "tokenType";
        Request request = chain.request().newBuilder()
                .header("app_key", "appId")
                .header("Authorization", tokenType + " " + accessToken)
                .header("Content-Type", "application/json")
                .addHeader("Connection", "close")
                .addHeader("Accept-Encoding", "identity")
                .build();
        return chain.proceed(request);
    }
}
