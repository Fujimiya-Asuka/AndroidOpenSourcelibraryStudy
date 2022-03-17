package com.asuka.http.Interceptor.log

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class HttpLogInterceptor:Interceptor {

    companion object{
        private const val TAG = "HttpLogInterceptor"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().build()
        Log.e(TAG, "发送请求url：${request.url()}，请求方法：${request.method()}")
        return chain.proceed(request)
    }
}