package com.asuka.androidopensourcelibrarystudydemo.utils;

import android.os.Build;

import com.asuka.androidopensourcelibrarystudydemo.BuildConfig;

import timber.log.Timber;

public class Logger {
    public static void init(){
        if (BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static void d(String s){
        Timber.d(s);
    }

}
