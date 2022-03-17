package com.asuka.androidopensourcelibrarystudydemo.modle.api

import com.asuka.androidopensourcelibrarystudydemo.modle.pojo.BaseResponse
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface WApi {
    //不适用转换器
    @POST("/user/login")
    @FormUrlEncoded
    fun login(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Observable<ResponseBody>

    //使用转换器，将回传数据ResponseBody直接转换为BaseResponse类型
    @POST("/user/login")
    @FormUrlEncoded
    fun login2(
        @Field("username") username: String?,
        @Field("password") password: String?
    ):Observable<BaseResponse>
}