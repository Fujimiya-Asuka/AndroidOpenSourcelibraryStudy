package com.asuka.androidopensourcelibrarystudydemo.modle.api;

import com.asuka.androidopensourcelibrarystudydemo.modle.pojo.BaseResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface WanAndroidApi {
    //不适用转换器
    @POST("/user/login")
    @FormUrlEncoded
    Call<ResponseBody> login(@Field("username")String username,@Field("password")String password);

    //使用转换器，将回传数据ResponseBody直接转换为BaseResponse类型
    @POST("/user/login")
    @FormUrlEncoded
    Call<BaseResponse> login2(@Field("username")String username,@Field("password")String password);
}
