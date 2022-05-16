package com.asuka.androidopensourcelibrarystudydemo.vlayout.kt

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.asuka.androidopensourcelibrarystudydemo.vlayout.Person

abstract class MyRecyclerViewAdapter<T:ViewBinding>(private var dataList:MutableList<Person>):
    RecyclerView.Adapter<MyRecyclerViewAdapter<T>.Holder<T>>() {

    inner class Holder<V:ViewBinding>(val viewBinding:V): RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder<T> {
        return Holder(onCreateViewBinding(parent))
    }

    override fun onBindViewHolder(holder: Holder<T>, position: Int) {
        onBindData(holder,position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    abstract fun onCreateViewBinding(parent: ViewGroup):T

    abstract fun onBindData(holder: Holder<T>, position: Int)


}