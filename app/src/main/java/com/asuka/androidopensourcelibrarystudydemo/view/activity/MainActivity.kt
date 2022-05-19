package com.asuka.androidopensourcelibrarystudydemo.view.activity

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityMainBinding
import com.asuka.androidopensourcelibrarystudydemo.vlayout.example.VLayoutActivity
import com.asuka.mqttlibrary.view.MQTTActivity
import com.teprinciple.mailsender.Mail
import com.teprinciple.mailsender.MailSender
import com.xlog.Utils
import com.xlog.XLog
import java.io.File

class MainActivity : BaseActivity<ActivityMainBinding>() {


    override fun initViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        XLog.d(filesDir.absolutePath)
        sendCrashFileToEmail()
    }

    override fun initListener() {
        clickAndSwitchActivity(binding.viewBindingBtn, ViewBindingActivity::class.java)
        clickAndSwitchActivity(binding.retrofitRxJavaBtn, RetrofitRxjavaActivity::class.java)
        clickAndSwitchActivity(binding.rxjavaBtn, RxjavaActivity::class.java)
        clickAndSwitchActivity(binding.retrofitBtn, RetrofitActivity::class.java)
        clickAndSwitchActivity(binding.okhttpBtn, OkHttpActivity::class.java)
        clickAndSwitchActivity(binding.cameraView, CameraViewActivity::class.java)
        clickAndSwitchActivity(binding.AndroidAsyncBtn, AndroidAsyncActivity::class.java)
        clickAndSwitchActivity(binding.RxNettyBtn, NettyActivity::class.java)
        clickAndSwitchActivity(binding.MulticastSocketBtn, MulticastSocketActivity::class.java)
        clickAndSwitchActivity(binding.mmkvBtn, MMKVActivity::class.java)
        clickAndSwitchActivity(binding.objectBoxBtn, ObjectBoxActivity::class.java)
        clickAndSwitchActivity(binding.matisseBtn, MyMatisseActivity::class.java)
        clickAndSwitchActivity(binding.mqttBtn, MQTTActivity::class.java)
        clickAndSwitchActivity(binding.navigationBtn, NavigationActivity::class.java)
        clickAndSwitchActivity(binding.serviceBtn, ServiceDemoActivity::class.java)
//        clickAndSwitchActivity(binding.handlerBtn, HandleActivity::class.java)
        clickAndSwitchActivity(binding.handlerBtn, VLayoutActivity::class.java)
    }

    override fun initData() {
    }

    /**
     * <pre>
     * desc:   点击按钮跳转Activity
     * Author:  XuZhenHui
     * Time:  2021/11/19 14:36
    </pre> *
     */
    private fun clickAndSwitchActivity(view: View, cls: Class<*>) {
        view.setOnClickListener { startActivity(Intent(this@MainActivity, cls)) }
    }


    fun sendCrashFileToEmail(){
        // 创建邮箱
        val mail = Mail().apply {
            mailServerHost = "smtp.qq.com"
            mailServerPort = "587"
            fromAddress = "389883128@qq.com"
            password = "oitguhncyerhcagj"
            toAddress = arrayListOf("fujimiya_asuka@outlook.com")
            subject = this@MainActivity.applicationContext.packageName
            for (file in File(filesDir.absolutePath+ File.separatorChar + "tombstones").listFiles()) {
                if (file != null) {
                    attachFiles.add(file)
                }
            }
        }
        // 发送邮箱
        MailSender.getInstance().sendMail(mail,object : MailSender.OnMailSendListener{
            override fun onError(e: Throwable) {
                Toast.makeText(this@MainActivity,"ERROR", Toast.LENGTH_SHORT).show()
            }

            override fun onSuccess() {
                Toast.makeText(this@MainActivity,"SUCCESS", Toast.LENGTH_SHORT).show()
                for (file in File(filesDir.absolutePath+ File.separatorChar + "tombstones").listFiles()) {
                    file?.delete()
                }
            }

        })
    }




}