package com.asuka.androidopensourcelibrarystudydemo.view.activity

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityServiceDemoBinding
import com.asuka.androidopensourcelibrarystudydemo.service.MyService

class ServiceDemoActivity : BaseActivity<ActivityServiceDemoBinding>() {

    private var mBinder:MyService.MyBinder?=null
    private var mService:MyService?=null

    private val conn = object : ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            binding.textView.text = "服务绑定"
            mBinder=service as MyService.MyBinder
            mService=mBinder?.getService()
        }
        override fun onServiceDisconnected(name: ComponentName?) {
            binding.textView.text="服务解绑"
        }
    }



    override fun initViewBinding(): ActivityServiceDemoBinding {
        return ActivityServiceDemoBinding.inflate(layoutInflater)
    }

    override fun initView() {

    }

    override fun initListener() {
        binding.bindBtn.setOnClickListener{
            val intent = Intent(this@ServiceDemoActivity,MyService::class.java)
            bindService(intent,conn, Service.BIND_AUTO_CREATE)
        }

        binding.startBtn.setOnClickListener{
            mBinder?.startWork()
        }

        binding.stopBtn.setOnClickListener{
            mBinder?.stopWork()
        }

        binding.getCountBtn.setOnClickListener{
            mBinder?.let {
                binding.textView.text = it.getCount().toString()
            }
        }

        binding.getCountBtn2.setOnClickListener{
            mService?.let {
                binding.textView.text = it.getCount().toString()
            }
        }

    }

    override fun initData() {

    }

    override fun onDestroy() {
        super.onDestroy()
        mBinder?.let {
            unbindService(conn)
        }
    }
}