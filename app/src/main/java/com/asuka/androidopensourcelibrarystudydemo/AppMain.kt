package com.asuka.androidopensourcelibrarystudydemo

import android.app.Application
import com.asuka.androidopensourcelibrarystudydemo.modle.db.MyBoxStore
import com.asuka.androidopensourcelibrarystudydemo.utils.Logger
import io.objectbox.android.AndroidObjectBrowser
import timber.log.Timber

class AppMain : Application() {
    override fun onCreate() {
        super.onCreate()
        //Timber初始化
        Logger.init()
        //ObjectBox初始化
        MyBoxStore.init(this)
        //是否开启ObjectBox调试
        if (BuildConfig.DEBUG){
            AndroidObjectBrowser(MyBoxStore.store).start(this)
            Timber.d("开启ObjectBox调试")
        }
        Timber.d("application on create")
    }
}