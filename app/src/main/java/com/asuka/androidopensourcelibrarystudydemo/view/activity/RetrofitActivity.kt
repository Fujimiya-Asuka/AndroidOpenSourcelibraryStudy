package com.asuka.androidopensourcelibrarystudydemo.view.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.fastjson.JSON
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityRetrofitBinding
import com.asuka.androidopensourcelibrarystudydemo.modle.api.Api
import com.asuka.androidopensourcelibrarystudydemo.modle.api.WApi
import com.asuka.androidopensourcelibrarystudydemo.modle.api.WanAndroidApi
import com.asuka.androidopensourcelibrarystudydemo.modle.pojo.BaseResponse
import com.asuka.androidopensourcelibrarystudydemo.utils.HTTP.MyCallBack
import com.asuka.androidopensourcelibrarystudydemo.utils.HTTP.RetrofitFactory
import com.asuka.http.Interceptor.log.HttpLogInterceptor
import com.asuka.http.RXHttp
import com.asuka.http.api.RxApi
import com.asuka.http.response.DialogResponse
import com.asuka.http.utils.NetWorkScheduler
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class RetrofitActivity : AppCompatActivity() {
    var binding: ActivityRetrofitBinding= ActivityRetrofitBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initListener()
    }

    private fun initListener() {

        binding.postWanBtn.setOnClickListener {
            RXHttp.Instance.baseUrl="https://www.wanandroid.com"
            RXHttp.Instance.addInterceptor(HttpLogInterceptor())
            RXHttp.Instance.custom(WApi::class.java).login("Fujimiya_Asuka","1593572580")
                .compose(NetWorkScheduler().ioMain())
                .subscribe(object : DialogResponse<ResponseBody>(this){
                    override fun onSuccess(data: ResponseBody) {
                        Timber.e(data.string())
                    }
                    override fun onFailure(e: Throwable) {
                        Timber.e(e)
                    }
                })
        }
    }

}