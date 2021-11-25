package com.asuka.androidopensourcelibrarystudydemo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityAndroidAsyncBinding;
import com.asuka.androidopensourcelibrarystudydemo.modle.HttpServer;

import java.net.HttpURLConnection;

public class AndroidAsyncActivity extends AppCompatActivity {

    ActivityAndroidAsyncBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAndroidAsyncBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_android_async);
        setContentView(binding.getRoot());

        binding.startServerBtn.setOnClickListener(view -> {
            HttpServer.getInstance().startServer();
        });

        binding.stopServerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpServer.getInstance().stopServer();
            }
        });

    }

}