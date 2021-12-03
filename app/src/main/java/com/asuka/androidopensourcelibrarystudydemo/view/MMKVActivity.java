package com.asuka.androidopensourcelibrarystudydemo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityMmkvactivityBinding;
import com.tencent.mmkv.MMKV;

import java.util.Random;

import timber.log.Timber;

public class MMKVActivity extends AppCompatActivity {
    private ActivityMmkvactivityBinding binding;
    private MMKV mmkv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMmkvactivityBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_mmkvactivity);
        setContentView(binding.getRoot());
        //初始化MMKV
        initMMKV();
        //获取MMKV全局实例
        mmkv = MMKV.defaultMMKV();
        initData();
        binding.addUpdateBtn.setOnClickListener(view ->addUpdate());
        binding.deleteBtn.setOnClickListener(view ->deleteByKey());
        binding.searchBtn.setOnClickListener(view ->searchByKey());


    }

    private void searchByKey() {
        int i=0;
        //获取所有key
        String[] strings = mmkv.allKeys();
        if (strings != null){
            for (String string : strings) {
                Timber.d("searchKey："+string+"   value："+mmkv.decodeString(string));
                i++;
            }
            Timber.d(""+i);
        }
        //根据Key查找value
        String s = mmkv.decodeString(binding.searchKeyEdit.getText().toString());
        Timber.d(s);
        binding.searchResultTv.setText(s);
    }

    private void deleteByKey() {
        //通过key删除
        mmkv.removeValueForKey(binding.deleteKeyEdit.getText().toString().trim());
    }

    private void addUpdate() {
        //添加或修改
//        mmkv.encode(binding.keyEdit.toString(),binding.valueEdit.toString());
        mmkv.encode(binding.keyEdit.getText().toString(),binding.valueEdit.getText().toString());
    }

    private void initData() {
        for (int i = 0; i < 1000; i++) {
            mmkv.encode(""+i,new Random().nextInt(1000)+"");
        }
    }

    private void initMMKV() {
        //初始化MMKV，可以在application中进行
        MMKV.initialize(getApplicationContext());
    }


}