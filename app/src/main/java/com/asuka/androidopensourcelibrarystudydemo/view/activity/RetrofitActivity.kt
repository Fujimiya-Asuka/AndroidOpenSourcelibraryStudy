package com.asuka.androidopensourcelibrarystudydemo.view.activity

import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityRetrofitBinding
import com.asuka.androidopensourcelibrarystudydemo.modle.api.HttpBinApi
import com.asuka.androidopensourcelibrarystudydemo.modle.api.WApi
import com.asuka.http.Interceptor.ConsumerInterceptor
import com.asuka.http.Interceptor.log.HttpLogInterceptor
import com.asuka.http.RXHttp
import com.asuka.http.response.DialogResponse
import com.asuka.http.utils.NetWorkScheduler
import okhttp3.ResponseBody
import timber.log.Timber

class RetrofitActivity : BaseActivity<ActivityRetrofitBinding>() {
    override fun initViewBinding(): ActivityRetrofitBinding {
        return ActivityRetrofitBinding.inflate(layoutInflater)
    }

    override fun initView() {

    }

    override fun initListener() {

        binding.postWanBtn.setOnClickListener {
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

        binding.temporaryReqBtn.setOnClickListener{
            Timber.d("temp")
            RXHttp.Instance.custom("https://httpbin.org/get/",HttpBinApi::class.java).getRequest()
                .compose(NetWorkScheduler().ioMain())
                .subscribe(object : DialogResponse<ResponseBody>(this){
                    override fun onSuccess(body: ResponseBody) {
                        Timber.d(body.string())
                    }

                    override fun onFailure(e: Throwable) {
                        Timber.d(e)
                    }

                })
        }

    }

    override fun initData() {
        RXHttp.Instance.baseUrl="https://www.wanandroid.com"
        RXHttp.Instance.addInterceptor(HttpLogInterceptor())
        RXHttp.Instance.addInterceptor(ConsumerInterceptor())
    }

}