package com.ljgandroid;

import com.ljgandroid.bean.JavaBean;
import com.ljgandroid.bean.SplashEntity;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Author:XWQ
 * Time   2019/1/15
 * Descrition: this is ApiService
 */

public interface ApiService
{
    @POST("action/apiv2/banner?catalog=1")
    Observable<JavaBean> getCall();

    /**
     * <p>http://static.owspace.com/static/picture_list.txt?client=android&version=1.3.0&time=1467864021&device_id=866963027059338</p>
     *
     * @param client
     * @param version
     * @param time
     * @param deviceId
     * @return
     */
    @GET("static/picture_list.txt")
    Observable<SplashEntity> getSplash(@Query("client") String client, @Query("version") String version, @Query("time") Long time, @Query("device_id") String deviceId);
}
