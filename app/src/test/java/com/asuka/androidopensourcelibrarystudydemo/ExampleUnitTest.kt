package com.asuka.androidopensourcelibrarystudydemo

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    fun test() {
        val observer: Observer<Int> = object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {
                println("onSubscribe")
            }

            override fun onNext(integer: Int) {
                println("onNext$integer")
            }

            override fun onError(e: Throwable) {
                println("onError")
            }

            override fun onComplete() {
                println("onComplete")
            }
        }
        val observable1 = Observable.create<Int> { emitter -> emitter.onNext(1) }
        observable1.subscribe(observer)
        val observable2 = Observable.create<Int> { emitter -> emitter.onNext(2) }
        val objectObservable = observable1.flatMap { observable2 }
        objectObservable.subscribe(observer)
        Observable.create<Int> { emitter -> emitter.onNext(3) }.flatMap { integer ->
            //flatMap(new Function<原事件类型, ObservableSource<新事件类型>>()
            //接受原始事件，进行处理
            println("收到事件$integer")
            //返回一个新的事件
            Observable.create<String> { emitter -> emitter.onNext("4") }
        }
            .subscribe { string -> println("接收到了事件$string") }
        println("==========================")
        Observable.just(1).subscribe(observer)
        println("==========================")
        Flowable.just(0).subscribe { integer -> println(integer) }
    }
}