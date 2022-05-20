package com.asuka.androidopensourcelibrarystudydemo.utils

import android.content.Context
import android.widget.Toast
import com.teprinciple.mailsender.Mail
import com.teprinciple.mailsender.MailSender
import com.xlog.XLog
import java.io.File
import java.util.*

class CrashFileHandle(var context: Context) {
    private val crashFiles = File(context.filesDir.absolutePath+File.separatorChar+"tombstones").listFiles()

    /**
     * 发送crash文件到指定邮箱 发送完毕后会删除所有的crash文件
     */
    fun sendCrashFileToEmail(){
        // 创建邮箱
        val mail = Mail().apply {
            mailServerHost = "smtp.qq.com"
            mailServerPort = "587"
            fromAddress = "389883128@qq.com"
            password = "oitguhncyerhcagj"
            toAddress = arrayListOf("fujimiya_asuka@outlook.com")
            subject = context.applicationContext.packageName
            if (crashFiles.isEmpty()){
                content="no crash file"
            }else{
                crashFiles?.let {
                    for (file in it) {
                        attachFiles.add(file)
                    }
                }
            }
        }
        // 发送邮箱
        MailSender.getInstance().sendMail(mail,object : MailSender.OnMailSendListener{
            override fun onError(e: Throwable) {
                Toast.makeText(context,"ERROR", Toast.LENGTH_SHORT).show()
            }
            override fun onSuccess() {
                Toast.makeText(context,"SUCCESS", Toast.LENGTH_SHORT).show()
                deleteCrashFile()
            }
        })
    }

    /**
     *  删除n天前的Crash日志，默认删除全部
     *  @param n n天前的日志
     */
    fun deleteCrashFile(n:Int=0){
        if (crashFiles.isNotEmpty()){
            when {
                n>0 -> {
                    for (file in crashFiles) {
                        if (Date(file.lastModified() + n * 24 * 3600 * 1000).before(Date())) {
                            file.delete()
                        }
                    }
                    return
                }
                n==0 -> {
                    for (file in crashFiles) {
                        file.delete()
                    }
                }
                else -> {
                    throw Exception("传入参数n不合法")
                }
            }
        }
    }
}