package com.asuka.androidopensourcelibrarystudydemo;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {

    private String message="";

    private int total;

    private T data;

    private int code;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
