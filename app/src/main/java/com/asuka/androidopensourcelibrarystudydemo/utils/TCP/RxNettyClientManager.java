package com.asuka.androidopensourcelibrarystudydemo.utils.TCP;

import java.sql.Timestamp;

import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.reactivex.netty.channel.Connection;
import io.reactivex.netty.protocol.tcp.client.TcpClient;
import rx.Observable;
import rx.Observer;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import timber.log.Timber;

public class RxNettyClientManager {
    private static volatile RxNettyClientManager rxNettyClientManager;
    private static volatile Connection mConnection;
    private static String host="localhost";
    private static int port = 6008;

    public static RxNettyClientManager getInstance(){
        if (rxNettyClientManager ==null){
            synchronized (RxNettyClientManager.class){
                if (rxNettyClientManager ==null){
                    rxNettyClientManager = new RxNettyClientManager();
                }
            }
        }
        return rxNettyClientManager;
    }

    /**
     *  <pre>
     *  desc:   netty客户端创建连接
     *  Author:  XuZhenHui
     *  Time:  2021/11/30 10:21
     *  </pre>
     */
    public void start(){
        if (mConnection==null){
            synchronized (RxNettyClientManager.class){
                if (mConnection==null){
                    Timber.d("开始连接");
                    TcpClient.newClient(host,port)
                            //添加编码
                            .<String,String>addChannelHandlerLast("string-decoder", new Func0<ChannelHandler>() {
                                @Override
                                public ChannelHandler call() {
                                    return new StringDecoder();
                                }
                            })
                            //添加解码
                            .<String,String>addChannelHandlerLast("string-encoder", new Func0<ChannelHandler>() {
                                @Override
                                public ChannelHandler call() {
                                    return new StringEncoder();
                                }
                            })
                            //创建连接
                            .createConnectionRequest()
                            .subscribe(new Action1<Connection<String, String>>() {
                                @Override
                                public void call(Connection<String, String> connection) {
                                    //保存连接
                                    mConnection = connection;
//                                    //接收服务器响应数据
//                                    mConnection.getInput().subscribe(new Action1<String>() {
//                                        @Override
//                                        public void call(String s) {
//                                            Timber.d("客户端接收数据"+s);
//                                        }
//                                    }, new Action1<Throwable>() {
//                                        @Override
//                                        public void call(Throwable throwable) {
//                                            Timber.d("接收数据失败");
//                                        }
//                                    }, new Action0() {
//                                        @Override
//                                        public void call() {
//                                            Timber.d("接收数据完成");
//                                        }
//                                    });
                                }
                            }, new Action1<Throwable>() {
                                @Override
                                public void call(Throwable throwable) {
                                    Timber.d("连接失败");
                                }
                            }, new Action0() {
                                @Override
                                public void call() {
                                    Timber.d("连接成功");
                                    receiverData();
                                }
                            });
                }
            }
        }
    }

    /**
     *  <pre>
     *  desc:   netty发送数据
     *  Author:  XuZhenHui
     *  Time:  2021/11/30 10:20
     *  </pre>
     */
    public boolean sendMessage(String msg){
        if (mConnection!=null){
            Timber.d("客户端"+mConnection+"发送数据"+msg);
            mConnection.write(Observable.just(msg)).subscribe(new Observer<String>() {
                @Override
                public void onCompleted() {
                    Timber.d("发送成功");
                }

                @Override
                public void onError(Throwable e) {
                    Timber.d("发送失败");
                }

                @Override
                public void onNext(String s) {
                    Timber.d(s);
                }
            });
            return true;
        }else {
            return false;
        }
    }

    private void receiverData(){
        mConnection.getInput().subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                Timber.d("A");
            }

            @Override
            public void onError(Throwable e) {
                Timber.d("接收数据失败,连接被关闭"+e);
                // TODO: 2021/11/30  重连：
                reConnect();
            }

            @Override
            public void onNext(String s) {
                Timber.d("客户端"+mConnection+"接收数据"+s);
            }
        });
    }

    /**
     *  <pre>
     *  desc:   重连
     *  Author:  XuZhenHui
     *  Time:  2021/11/30 14:43
     *  </pre>
     */
    private void reConnect(){
        if (mConnection!=null){
            mConnection.closeNow();
            mConnection=null;
        }

    }
}
