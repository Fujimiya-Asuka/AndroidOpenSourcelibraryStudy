package com.asuka.androidopensourcelibrarystudydemo

import android.app.Application
import android.util.Log
import com.asuka.androidopensourcelibrarystudydemo.modle.db.MyBoxStore
import com.xlog.XLog
import io.objectbox.android.AndroidObjectBrowser

class AppMain : Application() {

    override fun onCreate() {
        super.onCreate()
//        Log.d("TEST", "onCreate: "+XLog.logPath)
        logInit()
        dataBaseInit()
        xcrash.XCrash.init(this)
    }

    /**
    *  日志初始化
    */
    private fun logInit(){
        XLog.setLogFileSize(1000)
            .enableDiskLog(true)
            .init(this)
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
        }
    }

}