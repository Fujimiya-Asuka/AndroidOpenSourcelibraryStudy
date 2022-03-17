package com.asuka.http.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

interface RxApi {
    @GET("")
     fun get(): Observable<ResponseBody>

    @POST("post")
    @FormUrlEncoded
    fun post(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Observable<ResponseBody>
}