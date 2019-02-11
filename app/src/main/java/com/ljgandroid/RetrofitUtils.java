package com.ljgandroid;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ljgandroid.retrofit.interceptor.HttpHeaderInterceptor;
import com.ljgandroid.retrofit.interceptor.NetInterceptor;
import com.ljgandroid.retrofit.interceptor.NoNetInterceptor;
import com.ljgandroid.tools.Utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author:XWQ
 * Time   2019/1/4
 * Descrition: this is RetrofitUtils
 */

public class RetrofitUtils
{
    public static OkHttpClient.Builder getOkHttpClientBuilder()
    {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger()
        {
            @Override
            public void log(String message)
            {
                try
                {
                    Log.d("OKHttp", URLDecoder.decode(message, "utf-8"));
                } catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                    Log.e("OKHttp", message);
                }
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        File cacheFile = new File(Utils.getContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        return new OkHttpClient.Builder()
                .readTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new HttpHeaderInterceptor())//拦截请求 配置请求头
                .addInterceptor(new NoNetInterceptor())    //将无网络拦截器当做应用拦截器添加   okhttp只支持get请求缓存 post不支持 需自己利用数据库或者DiskLruCache 本地文件进行处理
                .addNetworkInterceptor(new NetInterceptor()) //将有网络拦截器当做网络拦截器添加
                //.sslSocketFactory(SslContextFactory.getSSLSocketFactoryForTwoWay())  // https认证 如果要使用https且为自定义证书 可以去掉这两行注释，并自行配制证书。
                // .hostnameVerifier(new SafeHostnameVerifier())
                .cache(cache);
    }

    public static Retrofit.Builder getRetrofitBuilder(String baseUrl)
    {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        OkHttpClient okHttpClient = RetrofitUtils.getOkHttpClientBuilder().build();
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl);
    }
}
