package com.asuka.androidopensourcelibrarystudydemo;

import com.alibaba.fastjson.JSON;
import com.asuka.androidopensourcelibrarystudydemo.modle.api.WanAndroidApi;
import com.asuka.androidopensourcelibrarystudydemo.modle.pojo.BaseResponse;
import com.asuka.androidopensourcelibrarystudydemo.modle.pojo.User;
import com.asuka.androidopensourcelibrarystudydemo.utils.HTTP.MyCallBack;
import com.asuka.androidopensourcelibrarystudydemo.utils.HTTP.RetrofitFactory;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.observable.ObservableNever;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void test() {

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(@NotNull Integer integer) {
                System.out.println("onNext"+integer);
            }

            @Override
            public void onError(@NotNull Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };

        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NotNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
            }
        });
        observable1.subscribe(observer);

        Observable<Integer> observable2 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NotNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(2);
            }
        });
        Observable<Integer> objectObservable = observable1.flatMap(new Function<Integer, ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> apply(@NotNull Integer integer) throws Exception {
                return observable2;
            }
        });
        objectObservable.subscribe(observer);

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NotNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            //flatMap(new Function<原事件类型, ObservableSource<新事件类型>>()
            @Override
            public ObservableSource<String> apply(@NotNull Integer integer) throws Exception {
                //接受原始事件，进行处理
                System.out.println("收到事件"+integer);
                //返回一个新的事件
                return Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NotNull ObservableEmitter<String> emitter) throws Exception {
                        emitter.onNext("4");
                    }
                });
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String string) throws Exception {
                System.out.println("接收到了事件"+string);
            }
        });
        System.out.println("==========================");
        Observable.just(1).subscribe(observer);
        System.out.println("==========================");
        Flowable.just(0).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });

        

    }
}