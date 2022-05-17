package com.asuka.androidopensourcelibrarystudydemo.vlayout.adapter.kt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asuka.androidopensourcelibrarystudydemo.R
import com.asuka.androidopensourcelibrarystudydemo.vlayout.bean.Person

open class MyAdapter(private val dataList:MutableList<Person>):RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_item,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}