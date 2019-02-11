package com.ljgandroid.retrofit;

import com.ljgandroid.bean.AnswerBean;
import com.ljgandroid.bean.JavaBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Author:XWQ
 * Time   2019/1/4
 * Descrition: this is GetRequest_Interface
 */

public interface GetRequest_Interface
{
    @POST("action/apiv2/banner?catalog=1")
    Observable<JavaBean> getCall();

    @POST("questionnaire/submitPaper.do")
    Observable<AnswerBean> getSplash
            (
                    @Query("test_id") String test_id,
                    @Query("answer_1") String answer_1,
                    @Query("answer_2") String answer_2,
                    @Query("answer_3") String answer_3,
                    @Query("answer_4") String answer_4,
                    @Query("answer_5") String answer_5,
                    @Query("answer_6") String answer_6,
                    @Query("answer_7") String answer_7,
                    @Query("answer_8") String answer_8,
                    @Query("answer_9") String answer_9,
                    @Query("answer_10") String answer_10,
                    @Query("answer_11") String answer_11,
                    @Query("answer_12") String answer_12,
                    @Query("answer_13") String answer_13,
                    @Query("answer_14") String answer_14,
                    @Query("answer_15") String answer_15,
                    @Query("username") String username,
                    @Query("phone") String phone
            );
}
