package com.xlog

import java.lang.Exception
import java.util.*

class Utils {
    companion object{
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