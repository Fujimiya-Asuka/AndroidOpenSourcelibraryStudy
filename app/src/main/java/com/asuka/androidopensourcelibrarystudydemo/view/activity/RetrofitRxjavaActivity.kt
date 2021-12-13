package com.asuka.androidopensourcelibrarystudydemo.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityRetrofitRxjavaBinding

class RetrofitRxjavaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRetrofitRxjavaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitRxjavaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}