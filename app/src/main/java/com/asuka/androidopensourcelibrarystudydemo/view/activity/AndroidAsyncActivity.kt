package com.asuka.androidopensourcelibrarystudydemo.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityAndroidAsyncBinding
import com.asuka.androidopensourcelibrarystudydemo.modle.HttpServer

class AndroidAsyncActivity:AppCompatActivity() {
    private lateinit var binding: ActivityAndroidAsyncBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAndroidAsyncBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startServerBtn.setOnClickListener { HttpServer.getInstance().startServer() }
        binding.stopServerBtn.setOnClickListener { HttpServer.getInstance().stopServer() }

    }

}