package com.asuka.androidopensourcelibrarystudydemo;

import org.junit.Test;

import java.net.URI;


public class javaTest {
    @Test
    public void javaTest(){
        method("1", new MyCallBack() {
            @Override
            public void onSuccess(String s) {
                System.out.println(s);
            }

            @Override
            public void onFailure(String s) {
                System.out.println(s);
            }
        });
    }

    public void method(String s,MyCallBack myCallBack){
        if ("1".equals(s)){
            myCallBack.onSuccess("success");
        }else {
            myCallBack.onFailure("failure");
        }
    }

    public interface MyCallBack{
        public void onSuccess(String s);
        public void onFailure(String s);
    }



}
