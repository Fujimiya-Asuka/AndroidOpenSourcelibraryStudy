package com.asuka.androidopensourcelibrarystudydemo.vlayout.example


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityVlayoutBinding
import com.asuka.androidopensourcelibrarystudydemo.databinding.RecycleViewItemBinding
import com.asuka.androidopensourcelibrarystudydemo.view.activity.BaseActivity
import com.asuka.androidopensourcelibrarystudydemo.vlayout.adapter.kt.SimpleDelegateAdapter
import com.asuka.androidopensourcelibrarystudydemo.vlayout.bean.Person


class VLayoutActivity : BaseActivity<ActivityVlayoutBinding>() {
    val personList = mutableListOf<Person>()
    lateinit var myAdapter: SimpleDelegateAdapter<Person, RecycleViewItemBinding>

    override fun initViewBinding(): ActivityVlayoutBinding {
        return ActivityVlayoutBinding.inflate(layoutInflater)
    }

    override fun initView() {
        


        for (i in (1..5)) {
            personList.add(Person("Tom",i))
        }

        myAdapter = object : SimpleDelegateAdapter<Person,RecycleViewItemBinding>(personList,LinearLayoutHelper()){

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
                binding.textView7.text="position$position"
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
            myAdapter.add(Person("Tom",personList.size+1))
            binding.recyclerView.smoothScrollToPosition(personList.size)
        }

        binding.subBtn.setOnClickListener{
            myAdapter.delete(personList.size)
        }
    }

    override fun initData() {
    }

}