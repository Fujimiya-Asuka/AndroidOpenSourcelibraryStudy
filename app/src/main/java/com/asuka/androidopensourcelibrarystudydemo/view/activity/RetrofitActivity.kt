package com.asuka.androidopensourcelibrarystudydemo.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.fastjson.JSON
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityRetrofitBinding
import com.asuka.androidopensourcelibrarystudydemo.modle.api.Api
import com.asuka.androidopensourcelibrarystudydemo.modle.api.WanAndroidApi
import com.asuka.androidopensourcelibrarystudydemo.modle.pojo.BaseResponse
import com.asuka.androidopensourcelibrarystudydemo.utils.HTTP.MyCallBack
import com.asuka.androidopensourcelibrarystudydemo.utils.HTTP.RetrofitFactory
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class RetrofitActivity : AppCompatActivity() {
    var binding: ActivityRetrofitBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(
            layoutInflater
        )
        //        setContentView(R.layout.activity_retrofit);
        setContentView(binding!!.root)
        val api = RetrofitFactory.getClient().create(
            Api::class.java
        )
        binding!!.getBtn.setOnClickListener { view: View? ->
            api["aaa", "bbb"].enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    try {
                        Timber.d(response.body().string())
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Timber.d(t)
                }
            })
        }
        binding!!.postBtn.setOnClickListener {
            api.post("123", "456").enqueue(object: MyCallBack<ResponseBody>() {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                }
            })
        }
        binding!!.postWanBtn.setOnClickListener {
            val wanAndroidClient = RetrofitFactory.getWanAndroidClient()
            val wanAndroidApi = wanAndroidClient.create(WanAndroidApi::class.java)
            //不使用自动数据类型的请求情况:
            wanAndroidApi.login("Fujimiya_Asuka", "1593572580")
                .enqueue(object : MyCallBack<ResponseBody>() {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        //需要自己手动将ResponseBody的数据转换为BaseResponse
                        var s: String? = null
                        if (response != null) {
                            try {
                                s = response.body().string()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        }
                        Timber.d("不使用自动数据类型的请求情况:")
                        val baseResponse = JSON.parseObject(s, BaseResponse::class.java)
                        Timber.d(baseResponse.toString())
                    }
                })
            //使用自动数据类型的请求情况:
            wanAndroidApi.login2("Fujimiya_Asuka", "1593572580")
                .enqueue(object : MyCallBack<BaseResponse>() {
                    override fun onResponse(
                        call: Call<BaseResponse>,
                        response: Response<BaseResponse>
                    ) {
                        Timber.d("使用自动数据类型的请求情况:")
                        //response的body就是BaseResponse类型的数据
                        val baseResponse = response.body()
                        Timber.d(baseResponse.toString())
                    }
                })
        }


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