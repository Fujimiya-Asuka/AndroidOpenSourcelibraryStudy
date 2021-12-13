package com.asuka.androidopensourcelibrarystudydemo.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityMulticastSocketBinding
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.io.IOException
import java.net.*

class MulticastSocketActivity : AppCompatActivity() {
    private var binding: ActivityMulticastSocketBinding? = null
    private var address: InetAddress? = null
    private var multicastSocket: MulticastSocket? = null
    private var compositeDisposable: CompositeDisposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMulticastSocketBinding.inflate(
            layoutInflater
        )
        //        setContentView(R.layout.activity_multicast_socket);
        setContentView(binding!!.root)
        initData()
        binding!!.receiveBtn.setOnClickListener { view: View? -> receiveData() }
        binding!!.sendBtn.setOnClickListener { view: View? -> sendData() }
    }

    /**
     * <pre>
     * desc:   变量初始化
     * Author:  XuZhenHui
     * Time:  2021/12/1 11:13
    </pre> *
     */
    private fun initData() {
        compositeDisposable = CompositeDisposable()
        try {
            address = InetAddress.getByName("239.0.0.155")
            multicastSocket = MulticastSocket(30016)
            multicastSocket!!.joinGroup(address)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * <pre>
     * desc:   发送组播数据
     * Author:  XuZhenHui
     * Time:  2021/12/1 11:12
    </pre> *
     */
    private fun sendData() {
        val s = "HelloWold"
        val bytes = s.toByteArray()
        var socket: DatagramSocket? = null
        try {
            socket = DatagramSocket()
        } catch (e: SocketException) {
            e.printStackTrace()
        }
        val datagramPacket = DatagramPacket(bytes, bytes.size, address, 30016)
        //发送
        val finalSocket = socket
        compositeDisposable!!.add(
            Observable.just(0)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    finalSocket!!.send(datagramPacket)
                    Timber.d("发送$s")
                }) { throwable -> Timber.d("发送失败$throwable") }
        )
    }

    /**
     * <pre>
     * desc:   接收组播数据
     * Author:  XuZhenHui
     * Time:  2021/12/1 11:12
    </pre> *
     */
    private fun receiveData() {
        val bytes = ByteArray(1024 * 1024)
        compositeDisposable!!.add(Flowable.just(1)
            .subscribeOn(Schedulers.io())
            .subscribe({
                while (true) {
                    val datagramPacket = DatagramPacket(bytes, bytes.size)
                    val s = String(datagramPacket.data).trim { it <= ' ' }
                    multicastSocket!!.receive(datagramPacket)
                    Timber.d("接收$s")
                }
            }) { throwable -> Timber.d("接收失败$throwable") }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable!!.clear()
    }
}