package com.asuka.androidopensourcelibrarystudydemo.modle;

import android.widget.Toast;

import com.asuka.androidopensourcelibrarystudydemo.modle.proto.MyProto;
import com.koushikdutta.async.callback.ListenCallback;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;

import java.io.File;
import java.sql.Timestamp;

import okhttp3.OkHttpClient;
import timber.log.Timber;

public class HttpServer implements HttpServerRequestCallback {

    private volatile static HttpServer httpServer;
    private volatile static AsyncHttpServer server = new AsyncHttpServer();
    private static final int PORT_LISTEN = 8080;

  /**
   *  <pre>
   *  desc:   获取HttpServer对象，单例模式
   *  Author:  XuZhenHui
   *  Time:  2021/11/25 16:31
   *  </pre>
   */
    public static synchronized HttpServer getInstance(){
        if (httpServer==null){
            synchronized (HttpServer.class){
                if (httpServer==null){
                    httpServer = new HttpServer();
                }
            }
        }
        return httpServer;
    }

    /**
     *  <pre>
     *  desc:   开启http服务
     *  Author:  XuZhenHui
     *  Time:  2021/11/25 16:43
     *  </pre>
     */
    public void startServer (){
        Timber.d("开启HTTP服务");
        //设置get请求接口，可以设置多个,callback是此类实现的HttpServerRequestCallback中的接口onRequest
        server.get("/a",this);
        server.get("/b",this);
        server.get("/c",this);
        //设置post请求接口
        server.post("/d",this);
        server.post("/e",this);
        //设置监听端口
        server.listen(PORT_LISTEN);
    }

    /**
     *  <pre>
     *  desc:   关闭http服务
     *  Author:  XuZhenHui
     *  Time:  2021/11/25 16:43
     *  </pre>
     */
    public void stopServer(){
        Timber.d("停止HTTP服务");
        Timber.d(server.toString());
        server.stop();

    }

    /**
     *  <pre>
     *  desc:   实现HttpServerRequestCallback接口的方法。对请求做出响应
     *  Author:  XuZhenHui
     *  Time:  2021/11/25 16:32
     *  </pre>
     */
    @Override
    public void onRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) {
        Timber.d("访问成功");
        switch (request.getPath()){
            case "/a" :
                Timber.d(request.getPath());
                break;
            case "/b" :
                Timber.d(request.getPath());
                break;
            case "/c" :
                Timber.d(request.getPath());
                break;
            case "/d" :
                Timber.d(request.getPath());
                break;
            case "/e" :
                Timber.d(request.getPath());
                break;
            default:
                break;
        }


        response.send("hello");
    }



}
