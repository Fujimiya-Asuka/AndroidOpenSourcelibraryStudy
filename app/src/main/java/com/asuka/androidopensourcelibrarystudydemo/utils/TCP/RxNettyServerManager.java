package com.asuka.androidopensourcelibrarystudydemo.utils.TCP;

import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.reactivex.netty.channel.Connection;
import io.reactivex.netty.protocol.tcp.server.ConnectionHandler;
import io.reactivex.netty.protocol.tcp.server.TcpServer;
import rx.Observable;
import rx.functions.Func0;
import rx.functions.Func1;
import timber.log.Timber;

public class RxNettyServerManager {
    private static volatile RxNettyServerManager rxNettyServerManager;
    private static volatile TcpServer<String, String> server;
    private static Connection mConnection;

    /**
     *  <pre>
     *  desc:   获取RxNettyManager实例
     *  Author:  XuZhenHui
     *  Time:  2021/11/30 11:03
     *  </pre>
     */
    public static RxNettyServerManager getInstance() {
        if (rxNettyServerManager == null) {
            synchronized (RxNettyServerManager.class) {
                if (rxNettyServerManager == null) {
                    rxNettyServerManager = new RxNettyServerManager();
                }
            }
        }
        return rxNettyServerManager;
    }

    /**
     *  <pre>
     *  desc:   开启Netty服务器 端口6008
     *  Author:  XuZhenHui
     *  Time:  2021/11/30 11:03
     *  </pre>
     */
    public void startServer() {
        if (server == null) {
            synchronized (RxNettyServerManager.class) {
                if (server == null) {
                    server = TcpServer.newServer(6008)
                            //编码
                            .<String, String>addChannelHandlerLast("string-decoder", new Func0<ChannelHandler>() {
                                @Override
                                public ChannelHandler call() {
                                    return new StringDecoder();
                                }
                            })
                            //解码
                            .<String, String>addChannelHandlerLast("string-encoder", new Func0<ChannelHandler>() {
                                @Override
                                public ChannelHandler call() {
                                    return new StringEncoder();
                                }
                            }).start(new ConnectionHandler<String, String>() {
                                @Override
                                public Observable<Void> handle(Connection<String, String> connection) {
                                    mConnection = connection;
                                    Timber.d("新连接加入："+connection.getInput().toString());
                                    return connection.writeAndFlushOnEach(connection.getInput().map(new Func1<String, String>() {
                                        @Override
                                        public String call(String string) {
                                            Timber.d("Server接收" + string);
                                            String s = "echo:" + string;
                                            Timber.d("服务器回应" + s);
                                            return s;
                                        }
                                    }));
                                }
                            });
                }
            }
            if (server!=null){
                Timber.d("服务器创建成功");
            }
        }

    }

    public void stopServer(){
        Timber.d("stopServer："+server);
        if (server!=null){
            server.shutdown();
            mConnection.closeNow();
            server=null;
            Timber.d("服务器关闭");
        }else {
            Timber.d("服务器已关闭");
        }

    }

}
