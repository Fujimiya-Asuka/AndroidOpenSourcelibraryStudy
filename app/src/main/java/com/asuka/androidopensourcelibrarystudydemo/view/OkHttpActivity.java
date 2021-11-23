package com.asuka.androidopensourcelibrarystudydemo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityOkHttpBinding;
import com.asuka.androidopensourcelibrarystudydemo.presenter.OkHttpActivityPresenter;
import com.asuka.androidopensourcelibrarystudydemo.utils.HTTP.OkHttpFactory;

import okhttp3.OkHttpClient;

public class OkHttpActivity extends AppCompatActivity {

    public ActivityOkHttpBinding binding;
    OkHttpClient client;
    OkHttpActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOkHttpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_ok_http);
        client = OkHttpFactory.getClient();
        presenter = new OkHttpActivityPresenter(this);
        binding.getBtn.setOnClickListener(view ->presenter.get());
        binding.postBtn.setOnClickListener(view -> presenter.post());
        binding.InterceptorBtn.setOnClickListener(view -> presenter.postWithInterceptor());
    }


}