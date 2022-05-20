package com.asuka.androidopensourcelibrarystudydemo.vlayout.adapter.kt


import androidx.viewbinding.ViewBinding
import com.alibaba.android.vlayout.LayoutHelper

// TODO: Developing Do not use it
abstract class BroccoliSimpleDelegateAdapter<T,VB:ViewBinding>(override var dataList:MutableList<T>, override var layoutHelper: LayoutHelper)
    :SimpleDelegateAdapter<T,VB>(dataList,layoutHelper) {

    override fun onBindDataOrListener(binding: VB, position: Int, dataList: MutableList<T>) {
        onBindBroccoli(binding)
    }

    /**
     *绑定占位符控件
     */
    abstract fun onBindBroccoli(binding: VB)

}