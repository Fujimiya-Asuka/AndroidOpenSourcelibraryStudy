package com.asuka.http.request

import com.asuka.http.RXHttp
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
* 请求基类
*/
abstract class BaseRequest<T:BaseRequest<T>>{
    lateinit var mRetrofit:Retrofit

    open fun build():T{
        mRetrofit=generateRetrofitBuilder()
            .baseUrl(RXHttp.Instance.baseUrl)
            .client(generateOkhttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return this as T
    }

    open fun build(url:String):T{
        mRetrofit=generateRetrofitBuilder()
            .baseUrl(url)
            .client(generateOkhttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return this as T
    }

    private fun generateRetrofitBuilder():Retrofit.Builder{
        return RXHttp.Instance.getRetrofitClientBuilder()
    }

    private fun generateOkhttpClient():OkHttpClient{
        return RXHttp.Instance.getOkhttpClientBuilder().build()
    }

}