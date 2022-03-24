package com.asuka.http

import android.util.Log
import com.asuka.http.request.CustomRequest
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * 默认的超时时间
 */
const val DEFAULT_TIMEOUT_MILLISECONDS = 15000L

/**
 * 默认重试次数
 */
const val DEFAULT_RETRY_COUNT = 0

/**
 * 默认重试叠加时间
 */
const val DEFAULT_RETRY_INCREASE_DELAY = 0

/**
 * 默认重试延时
 */
const val DEFAULT_RETRY_DELAY = 500

/**
 * 默认缓存失效时间：永久有效
 */
const val DEFAULT_CACHE_NEVER_EXPIRE = -1

class RXHttp private constructor() {
    //全局Url
    var baseUrl = ""
    var subUrl = ""
    private var mOkHttpClientBuilder = OkHttpClient.Builder()
    private var mRetrofitClientBuilder = Retrofit.Builder()

    companion object {
        val Instance by lazy(mode=LazyThreadSafetyMode.SYNCHRONIZED) { RXHttp() }
    }

    /**
     *  添加拦截器
     *  @param interceptor 拦截器
     */
    fun addInterceptor(interceptor: Interceptor): RXHttp {
        mOkHttpClientBuilder.addInterceptor(interceptor)
        return Instance
    }

    /**
     *  添加拦截器
     *  @param interceptorList 拦截器列表
     */
    fun addInterceptor(interceptorList: MutableList<Interceptor>): RXHttp {
        for (interceptor in interceptorList) {
            addInterceptor(interceptor)
        }
        return Instance
    }

    /**
     * 获取OkHttp建造者
     */
    fun getOkhttpClientBuilder(): OkHttpClient.Builder {
        return Instance.mOkHttpClientBuilder
    }

    /**
    *  获取Retrofit建造者
    */
    fun getRetrofitClientBuilder(): Retrofit.Builder {
        return Instance.mRetrofitClientBuilder
    }

    /**
     *  自定义请求，Retrofit API +RxJava请求
     *  @param service Retrofit API
     */
    fun <T> custom(service: Class<T>): T {
        return CustomRequest().build().create(service)
    }

    fun <T> custom(url:String,service: Class<T>): T {
        return CustomRequest().build(url).create(service)
    }

//    fun <T> custom(url:String): T {
//        return CustomRequest().build(url).create()
//    }

}

