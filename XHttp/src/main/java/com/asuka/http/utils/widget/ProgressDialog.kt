package com.asuka.androidopensourcelibrarystudydemo.view.widget



import android.app.ProgressDialog
import android.content.Context


class ProgressDialog{


    companion object{
        lateinit var dialog:ProgressDialog
    }


    fun show(context: Context){
        dialog = ProgressDialog(context)
        dialog.setMessage("加载中")
        dialog.show()
    }

    fun dismiss(){
        dialog.dismiss()
    }

}