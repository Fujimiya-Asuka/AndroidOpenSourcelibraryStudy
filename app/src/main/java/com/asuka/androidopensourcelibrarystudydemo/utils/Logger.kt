package com.asuka.androidopensourcelibrarystudydemo.utils

import com.asuka.androidopensourcelibrarystudydemo.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree

object Logger {
    fun init() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    fun d(s: String?) {
        Timber.d(s)
    }
}