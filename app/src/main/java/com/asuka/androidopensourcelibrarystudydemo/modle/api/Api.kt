package com.asuka.androidopensourcelibrarystudydemo.modle.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @GET("get")
    operator fun get(
        @Query("username") username: String?,
        @Query("password") password: String?
    ): Call<ResponseBody>

    @POST("post")
    @FormUrlEncoded
    fun post(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Call<ResponseBody>
}