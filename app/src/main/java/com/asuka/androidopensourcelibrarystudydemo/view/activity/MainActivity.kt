package com.asuka.androidopensourcelibrarystudydemo.view.activity

import android.content.Intent
import android.view.View
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityMainBinding
import com.asuka.androidopensourcelibrarystudydemo.vlayout.example.VLayoutActivity
import com.asuka.mqttlibrary.view.MQTTActivity
import com.xlog.XLog

class MainActivity : BaseActivity<ActivityMainBinding>() {


    override fun initViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        for (i in 1..5){
            XLog.d(filesDir.absolutePath)
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
        clickAndSwitchActivity(binding.serviceBtn, ServiceDemoActivity::class.java)
//        clickAndSwitchActivity(binding.handlerBtn, HandleActivity::class.java)
        clickAndSwitchActivity(binding.handlerBtn, VLayoutActivity::class.java)
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