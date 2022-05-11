package com.xlog

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.DiskLogAdapter
import com.orhanobut.logger.Logger
import com.teprinciple.mailsender.Mail
import com.teprinciple.mailsender.MailSender
import org.jetbrains.annotations.NotNull
import java.io.File
import java.text.DateFormat
import java.util.*

class XLog private constructor(){

    companion object{
        @SuppressLint("StaticFieldLeak")
        private lateinit var mContext:Context
        private var enableDiskLog = false
        private var enableConsoleLog = true

        var init:Boolean=false

        var logPath:String?=null
        get() {
            Utils.checkInit(field)
            return field
        }

        var logFileSize:Int=500

        var tag = "XLog"

        private val mXLog: XLog by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            XLog()
        }

        /**
        * 初始化
        */
        fun init(context:Context){
            mContext = context
            logPath = mContext.filesDir.absolutePath+File.separatorChar + "logger"
            if (enableConsoleLog){
                Logger.addLogAdapter(AndroidLogAdapter())
            }
            if (enableDiskLog){
                Logger.addLogAdapter(DiskLogAdapter())
            }
            init=true
        }

        // TODO: 时间格式化
        fun dataFormat(dateFormat: DateFormat):Companion{
            return this
        }

        // TODO: 定义TAG
        fun tag(tag:String):Companion{
            return this
        }

        // TODO: log整合
        fun t(tag: String?){
            Logger.t(tag)
        }

        fun d(message: String,vararg args:Any?){
            if (args==null){
                Logger.d(message)
            }else{
                Logger.d(message,args)
            }
        }

        fun e(message:String, vararg args:Any?, throwable: Throwable?=null){
            if (throwable==null){
                Logger.e(message,args)
            }else{
                Logger.e(throwable,message,args)
            }
        }

        fun i(message: String){
            Logger.i(message)
        }

        fun v(message: String){
            Logger.v(message)
        }

        fun w(message: String){
            Logger.w(message)
        }

        fun json(json:String){
            Logger.json(json)
        }

        fun xml(xml:String){
            Logger.xml(xml)
        }


        // TODO: 自动删除旧日志
        fun enableAutoDeleteLogFile(days:Int?=null):Companion{
            return this
        }

        // TODO: 日志导出
        @RequiresApi(Build.VERSION_CODES.M)
        fun copyLogFileToSD(context: Activity,filePath:String?=null){
            Utils.checkInit(mContext)
            // TODO: 检查权限 无权限动态申请
            val checkStrongPermission = Utils.checkStrongPermission(context)
            if (filePath==null){
                val SDLogPath = Environment.getExternalStorageDirectory().absolutePath + File.separatorChar + "logger"
            }else{

            }
        }

        /**
         * @param boolean 是否控制台输出 默认是
         */
        fun enableConsoleLog(boolean: Boolean):Companion{
            enableConsoleLog = boolean
            return this
        }

        /**
         * @param boolean 是否磁盘输出 默认否
         */
        fun enableDiskLog(boolean: Boolean):Companion{
            enableDiskLog = boolean
            return this
        }

        /**
         *  设置日志单文件大小 默认500kb
         *  范围100-10,000KB （10MB）
         */
        fun setLogFileSize(@NotNull size:Int):Companion{
            if (size !in 100..100000){
                throw Exception("the size must in 100 to 100000")
            }
            return this
        }

        /**
         * 删除log文件
         */
        fun deleteLogFile(){
            Utils.checkInit(logPath)
            if (File(logPath).exists()){
                for (list in File(logPath).listFiles()) {
                    list.delete()
                }
            }
        }

        /**
        * 发送log文件到邮箱
        */
        fun sendLogToEmail(){
            Utils.checkInit(mContext)
            // 创建邮箱
            val mail = Mail().apply {
                mailServerHost = "smtp.qq.com"
                mailServerPort = "587"
                fromAddress = "389883128@qq.com"
                password = "oitguhncyerhcagj"
                toAddress = arrayListOf("1071595679@qq.com")
                subject = mContext.applicationContext.packageName
                for (file in File(logPath).listFiles()) {
                    if (file != null) {
                        attachFiles.add(file)
                    }
                }
            }
            // 发送邮箱
            MailSender.getInstance().sendMail(mail)
        }

        /**
         * 删除x天前的日志，不填默认3天
         * @param x 多少天前
         */
        private fun deleteLogBeforeXDays(x : Int?=3) {
            Utils.checkInit(logPath)
            // 获取该目录下的所有文件
            val files = File(logPath).listFiles()
            if (files.isNotEmpty()) {
                for (file in files) {
                    // 如果文件的上一次修改日期在当前时间前3天，就删除该文件
                    if (x != null) {
                        if (Date(file.lastModified() + x * 24 * 3600 * 1000).before(Date())) {
                            Logger.d("删除日志")
                        }
                    }
                }
            }
        }


    }


}