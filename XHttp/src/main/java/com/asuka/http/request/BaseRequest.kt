package com.asuka.http.request

import com.asuka.http.RXHttp
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
* 请求基类
*/
abstract class BaseRequest<T:BaseRequest<T>>{
    var mUrl = RXHttp.Instance.baseUrl
    lateinit var mRetrofit:Retrofit


    open fun build():T{
        mRetrofit=generateRetrofitBuilder()
            .baseUrl(RXHttp.Instance.baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return this as T
    }

    private fun generateRetrofitBuilder():Retrofit.Builder{
        return RXHttp.Instance.getRetrofitClientBuilder()
    }

}