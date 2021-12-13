package com.asuka.androidopensourcelibrarystudydemo.modle.db

import android.content.Context
import com.asuka.androidopensourcelibrarystudydemo.modle.pojo.MyObjectBox
import io.objectbox.BoxStore

object MyBoxStore {
    lateinit var store:BoxStore

    fun init(context:Context){
        store= MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()
    }
}