package com.asuka.androidopensourcelibrarystudydemo.vlayout.kt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asuka.androidopensourcelibrarystudydemo.R
import com.asuka.androidopensourcelibrarystudydemo.vlayout.Person
import com.asuka.androidopensourcelibrarystudydemo.vlayout.adapter.RecyclerViewHolder

class MyAdapter(var dataList:MutableList<Person>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner  class ViewHolder(itemView: View):RecyclerViewHolder(itemView){

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