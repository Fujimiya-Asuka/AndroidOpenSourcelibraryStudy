package com.asuka.androidopensourcelibrarystudydemo;

import com.alibaba.fastjson.JSON;
import com.asuka.androidopensourcelibrarystudydemo.modle.api.WanAndroidApi;
import com.asuka.androidopensourcelibrarystudydemo.modle.pojo.BaseResponse;
import com.asuka.androidopensourcelibrarystudydemo.modle.pojo.User;
import com.asuka.androidopensourcelibrarystudydemo.utils.HTTP.MyCallBack;
import com.asuka.androidopensourcelibrarystudydemo.utils.HTTP.RetrofitFactory;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void test() {
        Retrofit wanAndroidClient = RetrofitFactory.getWanAndroidClient();
        WanAndroidApi wanAndroidApi = wanAndroidClient.create(WanAndroidApi.class);
        wanAndroidApi.login("Fujimiya_Asuka","1593572580").enqueue(new MyCallBack<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                System.out.println(response.body().toString());
            }
        });
        while (true){

        }
    }
}