package com.asuka.androidopensourcelibrarystudydemo.vlayout


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.asuka.androidopensourcelibrarystudydemo.R

import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityVlayoutBinding
import com.asuka.androidopensourcelibrarystudydemo.view.activity.BaseActivity
import com.asuka.androidopensourcelibrarystudydemo.vlayout.kt.MyAdapter
import com.asuka.androidopensourcelibrarystudydemo.vlayout.kt.MyDelegateAdapter
import com.asuka.androidopensourcelibrarystudydemo.vlayout.kt.MyRecyclerViewAdapter
import java.util.ArrayList

class VLayoutActivity : BaseActivity<ActivityVlayoutBinding>() {

    override fun initViewBinding(): ActivityVlayoutBinding {
        return ActivityVlayoutBinding.inflate(layoutInflater)
    }

    override fun initView() {

        var personList1 = mutableListOf<Person>()
        personList1.add(Person("小明",19))
        personList1.add(Person("小红",18))
        var delegateAdapter2 = object : MyRecyclerViewAdapter<ActivityVlayoutBinding>(personList1){
            override fun onCreateViewBinding(parent: ViewGroup): ActivityVlayoutBinding {
                return ActivityVlayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)

            }

            override fun onBindData(holder: Holder<ActivityVlayoutBinding>, position: Int) {
            }

        }

        val myAdapter = MyAdapter(personList1)
        binding.recycleView.layoutManager=LinearLayoutManager(this)
        binding.recycleView.adapter=myAdapter
    }

    override fun initListener() {
    }

    override fun initData() {
    }

}