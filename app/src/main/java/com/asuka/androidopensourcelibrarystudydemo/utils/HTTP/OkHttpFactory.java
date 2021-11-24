package com.asuka.androidopensourcelibrarystudydemo.utils.HTTP;

import com.asuka.androidopensourcelibrarystudydemo.utils.HTTP.Cookies.CookiesManager;

import okhttp3.OkHttpClient;

public class OkHttpFactory {

    private volatile static OkHttpClient client;

    //单例模式，双重锁
    public static OkHttpClient getClient(){
        if (client == null){
            synchronized (OkHttpFactory.class){
                if (client == null){
                    client = new OkHttpClient.Builder()
                            .cookieJar(new CookiesManager())
                            .build();
                }
            }
        }
        return client;
    }

//    public class Singleton {
//        private volatile static Singleton singleton;
//        private Singleton (){}
//        public static Singleton getSingleton() {
//            if (singleton == null) {
//                synchronized (Singleton.class) {
//                    if (singleton == null) {
//                        singleton = new Singleton();
//                    }
//                }
//            }
//            return singleton;
//        }
//    }

}