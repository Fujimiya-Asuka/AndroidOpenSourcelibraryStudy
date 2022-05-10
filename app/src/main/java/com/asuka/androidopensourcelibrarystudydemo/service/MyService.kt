package com.asuka.androidopensourcelibrarystudydemo.service

import android.app.Service
import android.content.Intent
import android.content.res.Configuration
import android.os.Binder
import android.os.IBinder
import timber.log.Timber
import java.io.FileDescriptor
import java.io.PrintWriter

class MyService:Service(){
    private var binding = false
    private var count=0
    private var startWork = true
    private val mBinder: MyBinder=MyBinder()

   inner class MyBinder: Binder() {
       fun getService():MyService{
           return this@MyService
       }
       fun startWork(){
           Timber.d("startWork")
       }
       fun stopWork(){
           Timber.d("stopWork")
       }
       fun getCount():Int{
           return count
       }
    }


    override fun onBind(intent: Intent?): IBinder? {
        binding=true
        return mBinder
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
    }

    override fun onCreate() {
        super.onCreate()
        Timber.d("myService on create")
        Thread{
            while (startWork) {
                count++
                try {
                    Thread.sleep(1000)
                } catch (e: Exception) {
                    Timber.d(e)
                }
            }
        }.start()
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Timber.d("myService on start")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Timber.d("onDestroy")
        super.onDestroy()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Timber.d("Unbind")
        startWork = false
        binding=false
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
    }

    override fun dump(fd: FileDescriptor?, writer: PrintWriter?, args: Array<out String>?) {
        super.dump(fd, writer, args)
    }

    fun getCount():Int{
        return if (binding){
            count
        }else{
            0
        }

    }

}