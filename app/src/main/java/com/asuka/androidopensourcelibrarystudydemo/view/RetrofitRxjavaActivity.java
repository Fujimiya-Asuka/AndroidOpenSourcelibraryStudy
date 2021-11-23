package com.asuka.androidopensourcelibrarystudydemo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityRetrofitRxjavaBinding;

public class RetrofitRxjavaActivity extends AppCompatActivity {

    private ActivityRetrofitRxjavaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityRetrofitRxjavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_retrofit_rxjava);


    }
}