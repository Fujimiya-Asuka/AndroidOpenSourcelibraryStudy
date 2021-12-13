package com.asuka.androidopensourcelibrarystudydemo

import android.app.Application
import com.asuka.androidopensourcelibrarystudydemo.utils.Logger
import timber.log.Timber

class AppMain : Application() {
    override fun onCreate() {
        super.onCreate()
        Logger.init()
        Timber.d("application on create")
    }
}