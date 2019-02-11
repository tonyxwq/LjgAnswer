package com.ljgandroid.dagger.components;

import com.ljgandroid.ApiService;
import com.ljgandroid.dagger.modules.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Author:XWQ
 * Time   2019/1/15
 * Descrition: this is NetComponent
 */

@Component(modules = NetModule.class)
@Singleton
public interface NetComponent {
    ApiService getApiService();
    OkHttpClient getOkHttp();
    Retrofit getRetrofit();
}
