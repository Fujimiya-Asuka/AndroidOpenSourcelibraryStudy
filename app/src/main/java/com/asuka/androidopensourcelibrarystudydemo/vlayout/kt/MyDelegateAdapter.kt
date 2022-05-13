package com.asuka.androidopensourcelibrarystudydemo.vlayout.kt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.LayoutHelper

abstract class MyDelegateAdapter<T,V:ViewBinding>(var dataList:MutableList<T>, private val layoutHelper: LayoutHelper) : DelegateAdapter.Adapter<MyDelegateAdapter<T,V>.Holder<V>>() {

    inner class Holder<V:ViewBinding>(val binding:V):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder<V> {
        return Holder(onCreateViewBinding(LayoutInflater.from(parent.context),parent))
    }

    override fun onBindViewHolder(holder: Holder<V>, position: Int) {
        onBindData(holder.binding,position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onCreateLayoutHelper(): LayoutHelper {
        return layoutHelper
    }

    abstract fun onCreateViewBinding(layoutInflater: LayoutInflater,parent: ViewGroup):V

    abstract fun onBindData(binding: V, position: Int)

}