package com.asuka.androidopensourcelibrarystudydemo.view.activity

import android.content.Intent
import android.os.Environment
import android.view.View
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityMainBinding
import com.asuka.mqttlibrary.view.MQTTActivity
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun initViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        for (index in 1..1000){
         Timber.d("弥漫着古老气息的山脉之中，几道身影闪电般的自半空中闪掠而过，隐隐间，透着一丝狼狈。$index")
        }
    }

    override fun initListener() {
        clickAndSwitchActivity(binding.viewBindingBtn, ViewBindingActivity::class.java)
        clickAndSwitchActivity(binding.retrofitRxJavaBtn, RetrofitRxjavaActivity::class.java)
        clickAndSwitchActivity(binding.rxjavaBtn, RxjavaActivity::class.java)
        clickAndSwitchActivity(binding.retrofitBtn, RetrofitActivity::class.java)
        clickAndSwitchActivity(binding.okhttpBtn, OkHttpActivity::class.java)
        clickAndSwitchActivity(binding.cameraView, CameraViewActivity::class.java)
        clickAndSwitchActivity(binding.AndroidAsyncBtn, AndroidAsyncActivity::class.java)
        clickAndSwitchActivity(binding.RxNettyBtn, NettyActivity::class.java)
        clickAndSwitchActivity(binding.MulticastSocketBtn, MulticastSocketActivity::class.java)
        clickAndSwitchActivity(binding.mmkvBtn, MMKVActivity::class.java)
        clickAndSwitchActivity(binding.objectBoxBtn, ObjectBoxActivity::class.java)
        clickAndSwitchActivity(binding.matisseBtn, MyMatisseActivity::class.java)
        clickAndSwitchActivity(binding.mqttBtn, MQTTActivity::class.java)
        clickAndSwitchActivity(binding.navigationBtn, NavigationActivity::class.java)
    }

    override fun initData() {

    }

    /**
     * <pre>
     * desc:   点击按钮跳转Activity
     * Author:  XuZhenHui
     * Time:  2021/11/19 14:36
    </pre> *
     */
    private fun clickAndSwitchActivity(view: View, cls: Class<*>) {
        view.setOnClickListener { startActivity(Intent(this@MainActivity, cls)) }
    }



}