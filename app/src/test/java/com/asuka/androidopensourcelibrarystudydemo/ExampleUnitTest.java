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

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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
                System.out.println("onNext");
                System.out.println(integer);
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

        Observable objectObservable = Observable.just(1)
                .flatMap(new Function<Integer, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<Integer> apply(@NotNull Integer integer) throws Exception {
                        System.out.println(integer);
                        return Observable.just(2);
                    }
                });

        objectObservable.subscribe(observer);

    }
}