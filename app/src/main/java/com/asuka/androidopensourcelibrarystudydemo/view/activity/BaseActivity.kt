package com.asuka.androidopensourcelibrarystudydemo.view.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T:ViewBinding>:AppCompatActivity() {
    private var _binding:T?=null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        _binding=initViewBinding()
        setContentView(binding.root)
        initView()
        initListener()
    }


    override fun onStart() {
        super.onStart()
        initData()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    abstract fun initViewBinding():T?


    abstract fun initView()

    abstract fun initListener()

    abstract fun initData()

}