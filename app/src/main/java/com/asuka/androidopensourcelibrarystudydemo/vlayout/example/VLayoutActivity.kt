package com.asuka.androidopensourcelibrarystudydemo.vlayout.example


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityVlayoutBinding
import com.asuka.androidopensourcelibrarystudydemo.databinding.RecycleViewItemBinding
import com.asuka.androidopensourcelibrarystudydemo.view.activity.BaseActivity
import com.asuka.androidopensourcelibrarystudydemo.vlayout.adapter.kt.SimpleDelegateAdapterKT
import com.asuka.androidopensourcelibrarystudydemo.vlayout.bean.Person


class VLayoutActivity : BaseActivity<ActivityVlayoutBinding>() {

    override fun initViewBinding(): ActivityVlayoutBinding {
        return ActivityVlayoutBinding.inflate(layoutInflater)
    }

    override fun initView() {
        

        val personList = mutableListOf<Person>()
        personList.add(Person("Tom",19))
        personList.add(Person("Jane",21))
        personList.add(Person("Jack",22))
        personList.add(Person("Tony",23))


        val myAdapter = object : SimpleDelegateAdapterKT<Person,RecycleViewItemBinding>(personList,GridLayoutHelper(4)){
            override fun onCreateViewBinding(
                parent: ViewGroup,
                viewType: Int
            ): RecycleViewItemBinding {
                return RecycleViewItemBinding.inflate(LayoutInflater.from(parent.context))
            }

            override fun onBindDataOrListener(
                binding: RecycleViewItemBinding,
                position: Int,
                dataList: MutableList<Person>
            ) {
                binding.textView5.text = dataList[position].name
                binding.textView5.setOnClickListener{
                    Toast.makeText(this@VLayoutActivity,"hello${dataList[position].name}",Toast.LENGTH_SHORT).show()
                }
            }

        }


        val virtualLayoutManager = VirtualLayoutManager(this)
        val delegateAdapter = DelegateAdapter(virtualLayoutManager)
        delegateAdapter.addAdapter(myAdapter)
        binding.recycleView.layoutManager=virtualLayoutManager
        binding.recycleView.adapter=delegateAdapter
    }

    override fun initListener() {
    }

    override fun initData() {
    }

}