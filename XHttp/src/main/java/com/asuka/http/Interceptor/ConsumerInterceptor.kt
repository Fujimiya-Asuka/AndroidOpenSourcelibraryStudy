package com.asuka.http.Interceptor


import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class ConsumerInterceptor:Interceptor {

    companion object{
        private  val TAG = this.javaClass.simpleName
    }

    override fun intercept(chain: Interceptor.Chain?): Response {

        //发送请求前置处理

        val request = chain!!.request().newBuilder()
            .addHeader("os", "android")
            .addHeader("version", "1.0.0")
            .build()
        return chain.proceed(request)
    }

}