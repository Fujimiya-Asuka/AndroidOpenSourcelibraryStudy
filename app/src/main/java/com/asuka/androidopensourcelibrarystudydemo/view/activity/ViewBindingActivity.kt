package com.asuka.androidopensourcelibrarystudydemo.view.activity

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityViewBindingBinding

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
    }

    override fun initData() {
    }
}