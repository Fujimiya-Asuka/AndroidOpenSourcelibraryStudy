package com.asuka.http.response

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class BaseResponse<T>:Observer<T>{

    abstract fun onSuccess(data:T)
    abstract fun onFailure(e:Throwable)

    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        onFailure(e)
    }

    override fun onComplete() {

    }
}