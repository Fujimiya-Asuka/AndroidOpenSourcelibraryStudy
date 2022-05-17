package com.asuka.androidopensourcelibrarystudydemo.vlayout.adapter.kt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.LayoutHelper

abstract class SimpleDelegateAdapterKT<T,VB:ViewBinding>(open var dataList:MutableList<T>, open var layoutHelper: LayoutHelper)
    : DelegateAdapter.Adapter<RecyclerViewHolderKT<VB>>() {

    open lateinit var holder: RecyclerViewHolderKT<VB>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolderKT<VB> {
        holder = RecyclerViewHolderKT(onCreateViewBinding(LayoutInflater.from(parent.context),parent,viewType))
        return holder
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
    abstract fun onCreateViewBinding(layoutInflater: LayoutInflater, parent: ViewGroup, viewType: Int):VB

    /**
     * 绑定数据
     */
    abstract fun onBindDataOrListener(binding: VB, position: Int, dataList: MutableList<T>)

    /**
     * 在末尾添加一条新数据
     */
    fun add(data:T){
        dataList.add(data)
        notifyItemInserted(dataList.size-1)
    }

    /**
     * 指定位置添加一条数据
     */
    fun add(data:T,position: Int){
        dataList.add(position,data)
        notifyItemInserted(position)
    }

    /**
     *  指定位置删除数据
     */
    fun delete(position: Int){
        if (dataList.size>0){
            dataList.removeAt(position-1)
            notifyItemChanged(position-1)
        }
    }

    /**
     * 刷新数据
     */
    fun refresh(data: T){
        dataList.clear()
        dataList.addAll(dataList)
        notifyDataSetChanged()
    }


    /**
     * 删除全部数据
     */
    fun deleteAll(){
        dataList.clear()
        notifyDataSetChanged()
    }

}