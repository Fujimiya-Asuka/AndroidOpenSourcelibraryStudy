package com.asuka.androidopensourcelibrarystudydemo.utils.Interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
//Okhttp拦截器
public class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //发送请求前置处理
        Request request = chain.request().newBuilder()
                .addHeader("os", "android")
                .addHeader("version", "1.0.0").build();
        Response proceed = chain.proceed(request);
        //发送请求后置处理

        return proceed;
    }
}
