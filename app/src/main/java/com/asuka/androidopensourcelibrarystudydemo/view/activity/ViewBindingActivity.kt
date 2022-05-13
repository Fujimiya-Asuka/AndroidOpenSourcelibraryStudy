package com.asuka.androidopensourcelibrarystudydemo.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asuka.androidopensourcelibrarystudydemo.R
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityViewBindingBinding
import com.asuka.androidopensourcelibrarystudydemo.vlayout.adapter.MyAdapter2

class ViewBindingActivity : BaseActivity<ActivityViewBindingBinding>() {
    override fun initViewBinding(): ActivityViewBindingBinding {
        return ActivityViewBindingBinding.inflate(layoutInflater)
    }

    open class RecycleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun initView() {
        binding.recyclerView.layoutManager=LinearLayoutManager(this)

    }

    override fun initListener() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }
}