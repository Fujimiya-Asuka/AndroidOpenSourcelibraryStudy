package com.asuka.androidopensourcelibrarystudydemo.utils.HTTP;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class RetrofitFactory {
    private volatile static Retrofit retrofitClient;
    private volatile static Retrofit wanAndroidClient;
    private volatile static Retrofit client;

    public static Retrofit getAndroidAsyncClient(){
        if (client==null){
            synchronized (RetrofitFactory.class){
                if (client==null){
                    client = new Retrofit.Builder()
                            .baseUrl("localhost:8080/")
                            .build();
                }
            }
        }
        return client;
    }

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
                            .addConverterFactory(FastJsonConverterFactory.create()) //添加转换器
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //添加适配器
                            .build();
                }
            }
        }
        return wanAndroidClient;
    }

}
