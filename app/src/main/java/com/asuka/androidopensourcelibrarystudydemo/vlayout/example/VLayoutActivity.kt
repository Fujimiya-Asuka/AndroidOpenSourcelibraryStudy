package com.asuka.androidopensourcelibrarystudydemo.vlayout.example


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityVlayoutBinding
import com.asuka.androidopensourcelibrarystudydemo.databinding.RecycleViewItemBinding
import com.asuka.androidopensourcelibrarystudydemo.view.activity.BaseActivity
import com.asuka.androidopensourcelibrarystudydemo.vlayout.adapter.kt.MyAdapter
import com.asuka.androidopensourcelibrarystudydemo.vlayout.adapter.kt.SimpleDelegateAdapterKT
import com.asuka.androidopensourcelibrarystudydemo.vlayout.bean.Person


class VLayoutActivity : BaseActivity<ActivityVlayoutBinding>() {
    val personList = mutableListOf<Person>()
    lateinit var myAdapter:SimpleDelegateAdapterKT<Person,RecycleViewItemBinding>

    override fun initViewBinding(): ActivityVlayoutBinding {
        return ActivityVlayoutBinding.inflate(layoutInflater)
    }

    override fun initView() {
        


        for (i in (1..5)) {
            personList.add(Person("Tom",i))
        }

        myAdapter = object : SimpleDelegateAdapterKT<Person,RecycleViewItemBinding>(personList,LinearLayoutHelper()){

            override fun onCreateViewBinding(
                layoutInflater: LayoutInflater,
                parent: ViewGroup,
                viewType: Int
            ): RecycleViewItemBinding {
                return RecycleViewItemBinding.inflate(layoutInflater,parent,false)
            }

            override fun onBindDataOrListener(
                binding: RecycleViewItemBinding,
                position: Int,
                dataList: MutableList<Person>
            ) {
                binding.textView5.text = dataList[position].name+dataList[position].age
                binding.textView5.setOnClickListener{
                    Toast.makeText(this@VLayoutActivity,"hello${dataList[position].name}",Toast.LENGTH_SHORT).show()
                }
            }



        }

        val virtualLayoutManager = VirtualLayoutManager(this)
        val delegateAdapter = DelegateAdapter(virtualLayoutManager)
        delegateAdapter.addAdapter(myAdapter)
        binding.recyclerView.layoutManager=virtualLayoutManager
        binding.recyclerView.adapter=delegateAdapter
    }

    override fun initListener() {
        binding.addBtn.setOnClickListener{
            myAdapter.add(Person("Jane",21))
            binding.recyclerView.smoothScrollToPosition(personList.size)
        }
    }

    override fun initData() {
    }

}