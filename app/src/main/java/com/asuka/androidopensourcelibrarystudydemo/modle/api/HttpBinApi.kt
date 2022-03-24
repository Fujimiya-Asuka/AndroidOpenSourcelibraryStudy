package com.asuka.androidopensourcelibrarystudydemo.modle.api

import com.asuka.androidopensourcelibrarystudydemo.modle.pojo.BaseResponse
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET

interface HttpBinApi {
    @GET("/")
    fun getRequest():Observable<ResponseBody>
}