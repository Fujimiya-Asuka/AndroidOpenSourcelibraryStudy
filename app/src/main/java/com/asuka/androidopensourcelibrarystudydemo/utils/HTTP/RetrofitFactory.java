package com.asuka.androidopensourcelibrarystudydemo.utils.HTTP;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private volatile static Retrofit retrofitClient;
    private volatile static Retrofit wanAndroidClient;

    public static Retrofit getClient(){
        if (retrofitClient==null){
            synchronized (RetrofitFactory.class){
                if (retrofitClient==null){
                    retrofitClient = new Retrofit.Builder()
                            .baseUrl("https://httpbin.org/")
                            .addConverterFactory(FastJsonConverterFactory.create())
                            .build();
                }
            }
        }
        return retrofitClient;
    }

    public static Retrofit getWanAndroidClient(){
        if (wanAndroidClient==null){
            synchronized (RetrofitFactory.class){
                if (wanAndroidClient==null){
                    wanAndroidClient = new Retrofit.Builder()
                            .baseUrl("https://www.wanandroid.com")
                            .client(OkHttpFactory.getClient())
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }
            }
        }
        return wanAndroidClient;
    }

}
