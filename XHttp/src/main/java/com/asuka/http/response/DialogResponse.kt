package com.asuka.http.response


import android.content.Context
import android.util.Log
import com.asuka.androidopensourcelibrarystudydemo.view.widget.ProgressDialog
import io.reactivex.disposables.Disposable
import java.net.UnknownHostException

abstract class DialogResponse<T>(
    private val mContext: Context
): BaseResponse<T>() {

    override fun onSubscribe(d: Disposable) {
        super.onSubscribe(d)
        ProgressDialog().show(mContext)
    }


    override fun onError(e: Throwable) {
        super.onError(e)
        ProgressDialog().dismiss()
    }

    override fun onComplete() {
        super.onComplete()
        ProgressDialog().dismiss()
    }
}