package com.asuka.xlog

import android.content.Context
import com.asuka.androidopensourcelibrarystudydemo.BuildConfig
import com.asuka.androidopensourcelibrarystudydemo.utils.FileLoggerTree
import timber.log.Timber
import timber.log.Timber.DebugTree
import java.io.File

class Logger private constructor() {
    private lateinit var mContext: Context
    private var fileMaxSize: Int = 2
    private var enableFileLog = false

    companion object {
        val Instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { Logger() }

    }

    fun init(context: Context) {
        mContext = context

        val logFile = File(getLogFilePath())

        //log文件大于所设置的最大文件大小就删除文件
        if (logFile.exists() && logFile.length() > Instance.fileMaxSize * 1024 * 1000) {
            logFile.delete()
        }

        if (BuildConfig.DEBUG && Instance.enableFileLog){
            Timber.plant(FileLoggerTree(mContext))
        }else{
            Timber.plant(DebugTree())
        }

    }


    fun d(s: String?) {
        Timber.d(s)
    }

    /**
     *  获取log文件路径
     */
    fun getLogFilePath(): String {
        return mContext.filesDir.canonicalPath + "/log.txt"
    }

    /**
     *  设置log文件最大存储大小 单位MB
     *  @param size 默认2MB，最大15MB
     *
     */
    fun setLoggerFileMaxSize(size: Int) {
        Instance.fileMaxSize = size
    }

    /**
    * @param boolean 是否开启日志存储文件
    */
    fun enableFileLog(boolean: Boolean){
        Instance.enableFileLog=boolean
    }

}