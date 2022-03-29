package com.asuka.androidopensourcelibrarystudydemo

import android.app.Application
import com.asuka.androidopensourcelibrarystudydemo.modle.db.MyBoxStore
import com.asuka.xlog.Logger
import io.objectbox.android.AndroidObjectBrowser
import timber.log.Timber

class AppMain : Application() {

    override fun onCreate() {
        super.onCreate()
        logInit()
        dataBaseInit()
        Timber.d("application on create")
    }

    /**
    *  日志初始化
    */
    private fun logInit(){
        //Timber初始化
        Logger.Instance.enableFileLog(true)
        Logger.Instance.setLoggerFileMaxSize(3)
        Logger.Instance.init(applicationContext)
    }

    /**
    *   数据库初始化
    */
    private fun dataBaseInit(){
        //ObjectBox初始化
        MyBoxStore.init(this)
        //是否开启ObjectBox调试
        if (BuildConfig.DEBUG){
            AndroidObjectBrowser(MyBoxStore.store).start(this)
            Timber.d("开启ObjectBox调试")
        }
    }

}