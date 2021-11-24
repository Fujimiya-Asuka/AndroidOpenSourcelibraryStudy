package com.asuka.androidopensourcelibrarystudydemo.modle.api;

import com.asuka.androidopensourcelibrarystudydemo.modle.pojo.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface WanAndroidApi {
    @POST("/user/login")
    @FormUrlEncoded
    Call<BaseResponse> login(@Field("username")String username,@Field("password")String password);
}
