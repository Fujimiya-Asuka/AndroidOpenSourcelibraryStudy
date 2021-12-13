package com.asuka.androidopensourcelibrarystudydemo

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Test

class test {
    @Test
    fun mian(){
        GlobalScope.launch {
            delay(1000)
            println("world")
        }
        println("hello")
        Thread.sleep(2000)
    }
}