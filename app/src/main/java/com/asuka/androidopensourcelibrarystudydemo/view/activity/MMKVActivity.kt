package com.asuka.androidopensourcelibrarystudydemo.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityMmkvactivityBinding
import com.tencent.mmkv.MMKV
import timber.log.Timber
import java.util.*

class MMKVActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMmkvactivityBinding
    private lateinit var mmkv: MMKV
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMmkvactivityBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //初始化MMKV
        initMMKV()
        //获取MMKV全局实例
        mmkv = MMKV.defaultMMKV()
        initData()
        binding.addUpdateBtn.setOnClickListener { addUpdate() }
        binding.deleteBtn.setOnClickListener { deleteByKey() }
        binding.searchBtn.setOnClickListener { searchByKey() }
    }

    private fun searchByKey() {
        var i = 0
        //获取所有key
        val strings = mmkv.allKeys()
        if (strings != null) {
            for (string in strings) {
                Timber.d("searchKey：" + string + "   value：" + mmkv!!.decodeString(string))
                i++
            }
            Timber.d("" + i)
        }
        //根据Key查找value
        val s = mmkv.decodeString(binding.searchKeyEdit.text.toString())
        Timber.d(s)
        binding.searchResultTv.text = s
    }

    private fun deleteByKey() {
        //通过key删除
        mmkv.removeValueForKey(binding.deleteKeyEdit.text.toString().trim { it <= ' ' })
    }

    private fun addUpdate() {
        //添加或修改
//        mmkv.encode(binding.keyEdit.toString(),binding.valueEdit.toString());
        mmkv.encode(binding.keyEdit.text.toString(), binding.valueEdit.text.toString())
    }

    private fun initData() {
        for (i in 0..999) {
            mmkv.encode("" + i, Random().nextInt(1000).toString() + "")
        }
    }

    private fun initMMKV() {
        //初始化MMKV，可以在application中进行
        MMKV.initialize(applicationContext)
    }
}