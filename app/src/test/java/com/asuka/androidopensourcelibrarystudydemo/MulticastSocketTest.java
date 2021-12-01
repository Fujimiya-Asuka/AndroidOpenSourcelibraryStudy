package com.asuka.androidopensourcelibrarystudydemo;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

import io.reactivex.disposables.CompositeDisposable;

public class MulticastSocketTest {

    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    private MulticastSocket sendSocket;
    private InetAddress sendAddress;
    private MulticastSocket receiveSocket1;
    private MulticastSocket receiveSocket2;
    private MulticastSocket receiveSocket3;
    private InetAddress receiveAddress;

    @Test
    public void test(){
        try {
            sendAddress = sendAddress.getByName("239.0.0.155");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            receiveSocket1 = new MulticastSocket(30016);
            receiveSocket2 = new MulticastSocket(30016);
            receiveSocket3 = new MulticastSocket(30017);
            receiveAddress = receiveAddress.getByName("239.0.0.155");
            receiveSocket1.joinGroup(receiveAddress);
            receiveSocket2.joinGroup(receiveAddress);
            receiveSocket3.joinGroup(receiveAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    receiveData(receiveSocket1);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    receiveData(receiveSocket2);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    receiveData(receiveSocket3);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sendData();
                }
            }
        }).start();

        while (true){

        }
    }

    private void sendData(){
        String s = "ABCDEFG";
        byte[] bytes = s.getBytes();
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length,sendAddress,30016);
        //发送
        try {
            socket.send(datagramPacket);
            System.out.println("发送"+s);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }

    }

    private void receiveData(MulticastSocket multicastSocket){
        byte[] bytes = new byte[1024*1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        try {
            multicastSocket.receive(datagramPacket);
            String s = new String(datagramPacket.getData()).trim();
            System.out.println(multicastSocket+"接收"+s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
