package com.asuka.androidopensourcelibrarystudydemo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityAndroidAsyncBinding;
import com.asuka.androidopensourcelibrarystudydemo.modle.HttpServer;
import com.asuka.androidopensourcelibrarystudydemo.modle.api.Api;
import com.asuka.androidopensourcelibrarystudydemo.modle.api.MyHttpServerApi;
import com.asuka.androidopensourcelibrarystudydemo.utils.HTTP.RetrofitFactory;

import java.net.HttpURLConnection;

import retrofit2.Retrofit;

public class AndroidAsyncActivity extends AppCompatActivity {
    private ActivityAndroidAsyncBinding binding;
    private MyHttpServerApi client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAndroidAsyncBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_android_async);
        setContentView(binding.getRoot());

        binding.startServerBtn.setOnClickListener(view -> HttpServer.getInstance().startServer());

        binding.stopServerBtn.setOnClickListener(view -> HttpServer.getInstance().stopServer());


    }

}