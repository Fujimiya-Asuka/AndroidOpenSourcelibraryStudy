package com.asuka.androidopensourcelibrarystudydemo.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
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
        clickAndSwitchActivity(binding.objectBoxBtn,ObjectBoxActivity::class.java)
        clickAndSwitchActivity(binding.matisseBtn,MyMatisseActivity::class.java)

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