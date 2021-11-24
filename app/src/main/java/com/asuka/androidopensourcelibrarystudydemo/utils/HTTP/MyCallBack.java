package com.asuka.androidopensourcelibrarystudydemo.utils.HTTP;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public abstract class MyCallBack<T> implements Callback<T> {
    @Override
    public void onFailure(Call call, Throwable t) {
        Timber.d(t);
    }
}
