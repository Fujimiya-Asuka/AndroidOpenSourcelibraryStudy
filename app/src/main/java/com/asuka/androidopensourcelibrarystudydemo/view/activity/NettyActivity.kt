package com.asuka.androidopensourcelibrarystudydemo.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityNettyBinding
import com.asuka.androidopensourcelibrarystudydemo.utils.TCP.RxNettyClientManager
import com.asuka.androidopensourcelibrarystudydemo.utils.TCP.RxNettyServerManager

class NettyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNettyBinding
    private lateinit var rxNettyClientManager: RxNettyClientManager
    private lateinit var rxNettyServerManager: RxNettyServerManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNettyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startNettyServerBtn.setOnClickListener {
            rxNettyServerManager = RxNettyServerManager.getInstance()
            rxNettyServerManager.startServer()
        }

        binding.startNettyClientBtn.setOnClickListener {
            Thread(
                Runnable { //安卓网络连接必须在非主线程中进行
                    rxNettyClientManager = RxNettyClientManager.getInstance()
                    rxNettyClientManager.start()
                }).start()
        }

        binding.clientSendMsgBtn.setOnClickListener {
            rxNettyClientManager.sendMessage(
                "Hello"
            )
        }

        binding.stopNettyServerBtn.setOnClickListener {
            rxNettyServerManager = RxNettyServerManager.getInstance()
            rxNettyServerManager.stopServer()
        }
    }
}