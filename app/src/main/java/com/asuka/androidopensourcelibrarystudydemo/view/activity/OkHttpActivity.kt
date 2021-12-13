package com.asuka.androidopensourcelibrarystudydemo.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityOkHttpBinding
import com.asuka.androidopensourcelibrarystudydemo.presenter.OkHttpActivityPresenter
import com.asuka.androidopensourcelibrarystudydemo.utils.HTTP.OkHttpFactory
import okhttp3.OkHttpClient

class OkHttpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOkHttpBinding
    private lateinit var client: OkHttpClient
    private lateinit var presenter: OkHttpActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOkHttpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        client = OkHttpFactory.getClient()
        presenter = OkHttpActivityPresenter(this)

        binding.getBtn.setOnClickListener { presenter.get() }
        binding.postBtn.setOnClickListener { presenter.post() }
        binding.InterceptorBtn.setOnClickListener { presenter.postWithInterceptor() }
    }

    fun showOnTextView(s: String?) {
        runOnUiThread(Thread { binding.okhttpTv.text = s })
    }
}