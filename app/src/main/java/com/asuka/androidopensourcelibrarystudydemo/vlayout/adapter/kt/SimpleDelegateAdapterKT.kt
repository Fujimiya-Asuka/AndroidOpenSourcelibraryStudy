package com.asuka.androidopensourcelibrarystudydemo.vlayout.adapter.kt

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.LayoutHelper

abstract class SimpleDelegateAdapterKT<T,VB:ViewBinding>(open var dataList:MutableList<T>, open var layoutHelper: LayoutHelper)
    : DelegateAdapter.Adapter<RecyclerViewHolderKT<VB>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolderKT<VB> {
        return RecyclerViewHolderKT(onCreateViewBinding(parent, viewType))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolderKT<VB>, position: Int) {
        onBindDataOrListener(holder.binding,position,dataList)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onCreateLayoutHelper(): LayoutHelper {
        return layoutHelper
    }

    /**
     * 创建视图
     */
    // XXXXXXXXBinding.inflate(LayoutInflater.from(parent.context))
    abstract fun onCreateViewBinding(parent: ViewGroup,viewType: Int):VB

    /**
     * 绑定数据
     */
    abstract fun onBindDataOrListener(binding: VB, position: Int, dataList: MutableList<T>)

}