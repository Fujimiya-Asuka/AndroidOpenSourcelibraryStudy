package com.asuka.androidopensourcelibrarystudydemo.vlayout.adapter.kt

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class RecyclerViewHolderKT<V:ViewBinding>(var binding:V): RecyclerView.ViewHolder(binding.root) {

}