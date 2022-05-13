package com.asuka.androidopensourcelibrarystudydemo.vlayout.adapter;

public interface ILoadListener {

    /**
     * 加载成功
     */
    void onLoadSuccess();

    /**
     * 加载失败
     *
     * @param error
     */
    void onLoadFailed(Throwable error);

}