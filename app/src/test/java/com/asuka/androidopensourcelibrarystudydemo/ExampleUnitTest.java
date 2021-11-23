package com.asuka.androidopensourcelibrarystudydemo;

import com.alibaba.fastjson.JSON;
import com.asuka.androidopensourcelibrarystudydemo.modle.pojo.User;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
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
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(aLong -> {
                    System.out.println(aLong);
                });
        while (true){

        }
    }
}