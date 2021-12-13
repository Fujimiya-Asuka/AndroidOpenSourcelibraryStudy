package com.asuka.androidopensourcelibrarystudydemo.utils.HTTP.Interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class MyInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        //发送请求前置处理
        var request = chain!!.request().newBuilder()
            .addHeader("os", "android")
            .addHeader("version", "1.0.0")
            .build()
        return chain.proceed(request)
    }
}