package com.asuka.androidopensourcelibrarystudydemo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        clickAndSwitchActivity(binding.viewBindingBtn, ViewBindingActivity.class);
        clickAndSwitchActivity(binding.retrofitRxJavaBtn, RetrofitRxjavaActivity.class);
        clickAndSwitchActivity(binding.rxjavaBtn, RxjavaActivity.class);
        clickAndSwitchActivity(binding.retrofitBtn, RetrofitActivity.class);
        clickAndSwitchActivity(binding.okhttpBtn,OkHttpActivity.class);
    }

    /**
     *  <pre>
     *  desc:   点击按钮跳转Activity
     *  Author:  XuZhenHui
     *  Time:  2021/11/19 14:36
     *  </pre>
     */
    private void clickAndSwitchActivity(View view,Class<?> cls){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,cls));
            }
        });
    }

}