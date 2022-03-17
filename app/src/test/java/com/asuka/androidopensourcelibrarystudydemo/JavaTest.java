package com.asuka.androidopensourcelibrarystudydemo;

import com.google.gson.Gson;

import org.junit.Test;

public class JavaTest {
    @Test
    public void toJson(){
        String s = "{\"code\":5000,\"data\":null,\"message\":\"密码错误,请重新填写\",\"total\":0}";
        BaseResponse<LoginResDto> loginResDtoBaseResponse = new BaseResponse<>();
        BaseResponse baseResponse = new Gson().fromJson(s, loginResDtoBaseResponse.getClass());
        System.out.println(baseResponse.getMessage());
    }
}
