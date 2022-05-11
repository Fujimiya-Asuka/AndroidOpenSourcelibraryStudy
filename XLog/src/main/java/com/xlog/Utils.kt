package com.xlog

import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import java.lang.Exception
import java.util.*

class Utils {
    companion object{
        @RequiresApi(Build.VERSION_CODES.M)
        fun checkStrongPermission(context:Activity):Boolean{
            var hasPermission = false
            context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            return hasPermission
        }
        fun checkInit(objects: Any?):Boolean{
            return if (objects==null){
                throw Exception("XLog not init")
                false
            }else{
                true
            }
        }
    }
}