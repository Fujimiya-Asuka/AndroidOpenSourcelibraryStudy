package com.asuka.androidopensourcelibrarystudydemo.modle

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.koushikdutta.async.http.server.AsyncHttpServerRequest
import com.koushikdutta.async.http.server.AsyncHttpServerResponse
import com.koushikdutta.async.http.server.HttpServerRequestCallback

class HttpServerKT:HttpServerRequestCallback {

    override fun onRequest(request: AsyncHttpServerRequest?, response: AsyncHttpServerResponse?) {
        when(request?.path){
            "/" -> Log.d(TAG, "onRequest: ")
            "//" -> Log.d(TAG, "onRequest: ")
            else->{
                println()
            }
        }
    }

}