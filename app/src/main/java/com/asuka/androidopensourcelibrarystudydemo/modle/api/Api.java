package com.asuka.androidopensourcelibrarystudydemo.modle.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @GET("get")
    Call<ResponseBody> get(@Query("username")String username,@Query("password")String password);

    @POST("post")
    @FormUrlEncoded
    Call<ResponseBody> post(@Field("username")String username,@Field("password")String password);

}
