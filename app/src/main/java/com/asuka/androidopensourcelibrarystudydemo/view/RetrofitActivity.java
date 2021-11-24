package com.asuka.androidopensourcelibrarystudydemo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.asuka.androidopensourcelibrarystudydemo.R;
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityRetrofitBinding;
import com.asuka.androidopensourcelibrarystudydemo.modle.api.Api;
import com.asuka.androidopensourcelibrarystudydemo.modle.api.WanAndroidApi;
import com.asuka.androidopensourcelibrarystudydemo.modle.pojo.BaseResponse;
import com.asuka.androidopensourcelibrarystudydemo.utils.HTTP.MyCallBack;
import com.asuka.androidopensourcelibrarystudydemo.utils.HTTP.RetrofitFactory;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import timber.log.Timber;

public class RetrofitActivity extends AppCompatActivity {

    public ActivityRetrofitBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRetrofitBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_retrofit);
        setContentView(binding.getRoot());
        Api api = RetrofitFactory.getClient().create(Api.class);

        binding.getBtn.setOnClickListener(view -> {
            api.get("aaa","bbb").enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        Timber.d(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Timber.d(t);
                }
            });
        });

        binding.postBtn.setOnClickListener(view -> {
            api.post("123","456").enqueue(new MyCallBack<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                }
            });
        });

        binding.postWanBtn.setOnClickListener(view -> {
            Retrofit wanAndroidClient = RetrofitFactory.getWanAndroidClient();
            WanAndroidApi wanAndroidApi = wanAndroidClient.create(WanAndroidApi.class);
            wanAndroidApi.login("Fujimiya_Asuka","1593572580").enqueue(new MyCallBack<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    Timber.d(response.body().toString());
                }
            });
        });

//        binding.postBtn.setOnClickListener(view -> api.post("123","456").enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    Timber.d(response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Timber.d(t);
//            }
//        }));

    }
}