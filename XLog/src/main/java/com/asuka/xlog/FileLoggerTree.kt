package com.asuka.androidopensourcelibrarystudydemo.utils

import android.content.Context
import android.os.Build
import android.os.Environment
import android.util.Log
import timber.log.Timber
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*
import java.util.regex.Pattern

class FileLoggerTree(val context: Context): Timber.DebugTree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        getBufferWriter().use {
            it.write(Date(System.currentTimeMillis()).toString()+"--> $tag -->"+message+"\r\n")
        }
        super.log(priority, tag, message, t)
    }

    private fun getBufferWriter():BufferedWriter{
        return BufferedWriter(OutputStreamWriter(context.openFileOutput("log.txt", Context.MODE_APPEND)))
    }

}