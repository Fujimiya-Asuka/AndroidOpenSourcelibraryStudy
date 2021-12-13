package com.asuka.androidopensourcelibrarystudydemo.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityKotlinBinding

class KotlinActivity : AppCompatActivity() {
    lateinit var binding:ActivityKotlinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKotlinBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}