package com.asuka.androidopensourcelibrarystudydemo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityMulticastSocketBinding;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MulticastSocketActivity extends AppCompatActivity {

    private ActivityMulticastSocketBinding binding;
    private InetAddress address;
    private MulticastSocket multicastSocket;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMulticastSocketBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_multicast_socket);
        setContentView(binding.getRoot());
        initData();
        binding.receiveBtn.setOnClickListener(view -> receiveData());
        binding.sendBtn.setOnClickListener(view -> sendData());

    }

    /**
     *  <pre>
     *  desc:   变量初始化
     *  Author:  XuZhenHui
     *  Time:  2021/12/1 11:13
     *  </pre>
     */
    private void initData() {
        compositeDisposable = new CompositeDisposable();
        try {
            address = address.getByName("239.0.0.155");
            multicastSocket = new MulticastSocket(30016);
            multicastSocket.joinGroup(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  <pre>
     *  desc:   发送组播数据
     *  Author:  XuZhenHui
     *  Time:  2021/12/1 11:12
     *  </pre>
     */
    private void sendData(){
        String s = "HelloWold";
        byte[] bytes = s.getBytes();
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, address,30016);
        //发送
        DatagramSocket finalSocket = socket;
        compositeDisposable.add(Observable.just(0)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        finalSocket.send(datagramPacket);
                        Timber.d("发送" + s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.d("发送失败"+throwable);
                    }
                })
        );
    }

    /**
     *  <pre>
     *  desc:   接收组播数据
     *  Author:  XuZhenHui
     *  Time:  2021/12/1 11:12
     *  </pre>
     */
    private void receiveData(){
        byte[] bytes = new byte[1024*1024];
        compositeDisposable.add(Flowable.just(1)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer I) throws Exception {

                        while (true) {
                            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
                            String s = new String(datagramPacket.getData()).trim();
                            multicastSocket.receive(datagramPacket);
                            Timber.d("接收" + s);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.d("接收失败"+throwable);
                    }
                })
        );




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}