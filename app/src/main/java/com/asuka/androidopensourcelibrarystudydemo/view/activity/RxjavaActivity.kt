package com.asuka.androidopensourcelibrarystudydemo.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityRxjavaBinding
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RxjavaActivity : AppCompatActivity() {
    private var compositeDisposable: CompositeDisposable? = null
    private lateinit var binding: ActivityRxjavaBinding
    private var emitter: FlowableEmitter<Int>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRxjavaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        compositeDisposable = CompositeDisposable()
        compositeDisposable!!.add(Flowable.create<Int>(
            { e -> emitter = e },
            BackpressureStrategy.BUFFER
        )
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe { integer ->
                runOnUiThread {
                    binding.textView.text = "" + integer
                }
            })
        binding.FlowableBtn.setOnClickListener {
            Thread {
                for (i in 0..499) {
                    try {
                        Thread.sleep(1000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    emitter!!.onNext(i)
                }
            }.start()
        }
    }
}