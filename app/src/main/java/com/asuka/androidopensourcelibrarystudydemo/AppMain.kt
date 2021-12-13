package com.asuka.androidopensourcelibrarystudydemo;

import android.app.Application;

import com.asuka.androidopensourcelibrarystudydemo.utils.Logger;

import timber.log.Timber;

public class AppMain extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init();
        Timber.d("application on create");
    }
}
