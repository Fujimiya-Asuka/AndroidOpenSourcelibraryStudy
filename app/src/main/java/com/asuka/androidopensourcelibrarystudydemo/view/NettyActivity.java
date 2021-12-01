package com.asuka.androidopensourcelibrarystudydemo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityNettyBinding;
import com.asuka.androidopensourcelibrarystudydemo.utils.TCP.RxNettyClientManager;
import com.asuka.androidopensourcelibrarystudydemo.utils.TCP.RxNettyServerManager;

import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.netty.channel.Connection;
import io.reactivex.netty.protocol.tcp.server.ConnectionHandler;
import io.reactivex.netty.protocol.tcp.server.TcpServer;
import io.reactivex.schedulers.Schedulers;
import rx.functions.Func0;
import rx.functions.Func1;
import timber.log.Timber;

public class NettyActivity extends AppCompatActivity {

    ActivityNettyBinding binding;
    Connection serverConnect;
    private RxNettyClientManager rxNettyClientManager;
    private RxNettyServerManager rxNettyServerManager;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNettyBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_netty);
        setContentView(binding.getRoot());

        binding.startNettyServerBtn.setOnClickListener(view -> {
            rxNettyServerManager = RxNettyServerManager.getInstance();
            rxNettyServerManager.startServer();
        });

        binding.startNettyClientBtn.setOnClickListener(view -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //安卓网络连接必须在非主线程中进行
                    rxNettyClientManager = RxNettyClientManager.getInstance();
                    rxNettyClientManager.start();
                }
            }).start();
        });

        binding.clientSendMsgBtn.setOnClickListener(view -> {
            rxNettyClientManager.sendMessage("Hello");
        });

        binding.stopNettyServerBtn.setOnClickListener(view -> {
            rxNettyServerManager = RxNettyServerManager.getInstance();
            rxNettyServerManager.stopServer();
        });

    }




}