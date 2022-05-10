package com.asuka.androidopensourcelibrarystudydemo.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityHandleBinding
private const val TAG = "TEST"
class HandleActivity : BaseActivity<ActivityHandleBinding>() {
    private var currentNum = 0
    private val handler = Handler(object : Handler.Callback{
        override fun handleMessage(msg: Message?): Boolean {
            Log.d(TAG, "handleMessage: "+msg?.what+msg+Thread.currentThread().name)
            msg?.let {
                currentNum--
                binding.textViewNum.text=currentNum.toString()
            }
            return true
        }

    })


    override fun initViewBinding(): ActivityHandleBinding {
        return ActivityHandleBinding.inflate(layoutInflater)
    }

    override fun initView() {
    }

    override fun initListener() {
        binding.buttonAdd.setOnClickListener{
            currentNum++
            handler.post{
                binding.textViewNum.text=currentNum.toString()
            }
        }

        binding.buttonSub.setOnClickListener{
            Thread{
                Log.d(TAG, "initListener: "+Thread.currentThread().name)
                handler.sendEmptyMessage(-1)
            }.start()
        }



    }

    override fun initData() {

    }
}