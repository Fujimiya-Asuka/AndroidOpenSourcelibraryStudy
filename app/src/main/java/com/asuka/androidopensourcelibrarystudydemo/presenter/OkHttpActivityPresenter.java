package com.asuka.androidopensourcelibrarystudydemo.presenter;

import androidx.appcompat.app.AppCompatActivity;

import com.asuka.androidopensourcelibrarystudydemo.utils.HTTP.OkHttpFactory;
import com.asuka.androidopensourcelibrarystudydemo.view.activity.OkHttpActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

public class OkHttpActivityPresenter {
    OkHttpActivity activity;
    private OkHttpClient client;

    public OkHttpActivityPresenter(AppCompatActivity activity) {
        client = OkHttpFactory.getClient();
        this.activity=(OkHttpActivity) activity;
    }

    public void get(){
        Request request = new Request.Builder().url("https://httpbin.org/get").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Timber.d(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s= response.body().string();
                Timber.d(s);
                activity.showOnTextView(s);
            }
        });
    }

    public void post(){
        FormBody formBody = new FormBody.Builder()
                .add("username", "Fujimiya_Asuka")
                .add("password", "1593572580").build();
        Request request = new Request.Builder()
                .post(formBody)
                .url("https://www.wanandroid.com/user/login")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Timber.d(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Timber.d(response.toString());
                String s = response.body().string();
                Timber.d(s);
                activity.showOnTextView(s);
            }
        });
    }

    public void postWithInterceptor(){
        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(new MyInterceptor())
//                .addNetworkInterceptor(new MyInterceptor())
                .build();
        FormBody formBody = new FormBody.Builder()
                .add("a", "1")
                .add("b", "2").build();
        Request request = new Request.Builder()
                .url("https://httpbin.org/post")
                .post(formBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Timber.d(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                Timber.d(s);
                activity.showOnTextView(s);
            }
        });
    }


}
