package com.asuka.androidopensourcelibrarystudydemo.modle.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MyHttpServerApi {
    @GET("/a")
    Call<ResponseBody> a();
}
