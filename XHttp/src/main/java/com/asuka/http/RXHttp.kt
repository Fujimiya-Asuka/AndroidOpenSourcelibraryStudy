package com.asuka.http

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
    private var interceptors = mutableListOf<Interceptor>()

    private object Holder {
        val Instance = RXHttp()
    }

    companion object {
        val Instance by lazy { Holder.Instance }

    }

    /**
     *  添加拦截器
     */
    fun addInterceptor(interceptor: Interceptor): RXHttp {
        interceptors.add(interceptor)
        return this
    }

    /**
     *  构建OkHttp客户端
     */
    private fun buildOkHttpClient(): OkHttpClient {
        for (interceptor in interceptors) {
            mOkHttpClientBuilder.addInterceptor(interceptor)
        }
        return mOkHttpClientBuilder.build()
    }

    /**
     *  构建Retrofit客户端
     */
    private fun buildRetrofitClient(): Retrofit {
        return mRetrofitClientBuilder
            .baseUrl(baseUrl)
            .client(buildOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getOkhttpClientBuilder(): OkHttpClient.Builder {
        return mOkHttpClientBuilder
    }

    fun getRetrofitClientBuilder(): Retrofit.Builder {
        return mRetrofitClientBuilder
    }

    fun getOkHttpClient(): OkHttpClient {
        return Instance.mOkHttpClientBuilder.build()
    }

    fun getRetrofitClient(): Retrofit {
        return buildRetrofitClient()
    }

    /**
     *  自定义请求，Retrofit API +RxJava请求
     *  @param service Retrofit API
     */
    fun <T> custom(service: Class<T>): T {
        return CustomRequest().build().create(service)
    }

}

