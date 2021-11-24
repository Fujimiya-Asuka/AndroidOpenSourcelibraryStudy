package com.asuka.androidopensourcelibrarystudydemo.utils.HTTP.Cookies;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import timber.log.Timber;

public class CookiesManager implements CookieJar {

    private final HashMap<String,List<Cookie>> cookiesStore = new HashMap<>();

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null){
            cookiesStore.put(url.host(),cookies);
            Log.d("TAG", "saveFromResponse: "+cookies);
        }

    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookiesStore.get(url.host());
        return cookies!=null?cookies : new ArrayList<Cookie>();
    }
}
