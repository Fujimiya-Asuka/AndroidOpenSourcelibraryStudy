package com.asuka.http.request

import retrofit2.create


class CustomRequest:BaseRequest<CustomRequest>(){

    fun <T> create(service:Class<T>):T{
        return mRetrofit.create(service)
    }


}