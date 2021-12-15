package com.asuka.androidopensourcelibrarystudydemo.view.activity

import android.Manifest
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.asuka.androidopensourcelibrarystudydemo.R
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityMyMatisseBinding
import com.tbruyelle.rxpermissions2.RxPermissions
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine
import com.zhihu.matisse.internal.entity.CaptureStrategy
import timber.log.Timber

class MyMatisseActivity : AppCompatActivity() {
    private val CHOOSE_CODE = 0
    private lateinit var binding:ActivityMyMatisseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMyMatisseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            if (checkPermissions()){
                selectPhoto()
            }else{
                requestPermission()
            }
        }

    }

    /**
     *  <pre>
     *  desc:   申请权限
     *  Author:  XuZhenHui
     *  Time:  2021/12/15 11:25
     *  </pre>
     */
    private fun requestPermission(){
        RxPermissions(this).request(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        ).subscribe({
            Timber.d("申请成功$it")
        },{
            Timber.d("申请失败$it")
        }).dispose()
    }

    /**
     *  <pre>
     *  desc:   打开Matiess选择图片
     *  Author:  XuZhenHui
     *  Time:  2021/12/15 11:29
     *  </pre>
     */
    private fun selectPhoto(){
        Matisse.from(this@MyMatisseActivity)
                .choose(MimeType.ofAll(), false) //参数1：图片MIME类型集，参数2：是否可以选择不同类型的图片
                .countable(true)    //开启计数 true显示数字，false显示打钩
                .maxSelectable(9)   //最大选择数
                .capture(true)  //选择图片时是否可拍照
                .captureStrategy(CaptureStrategy(true, "com.example.jie.photopickerdemo.fileprovider"))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                .gridExpectedSize(resources.getDimensionPixelSize(R.dimen.album_item_height))  //设置预览网格大小
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)   //限制方向
                .thumbnailScale(0.5f)   //缩略图缩放比例 默认0.5
                .showPreview(false)  //是否开启点击图片预览大图 默认true
                .autoHideToolbarOnSingleTap(true) //预览图片时是否开启单击图片隐藏工具栏
                .imageEngine(GlideEngine()) //图片加载引擎
                .forResult(CHOOSE_CODE); //请求码
    }

    /**
     *  <pre>
     *  desc:   接收选择图片后返回的结果
     *  Author:  XuZhenHui
     *  Time:  2021/12/15 11:35
     *  </pre>
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CHOOSE_CODE && resultCode == RESULT_OK) {
            Timber.d("uris" + Matisse.obtainPathResult(data))
            Timber.d("Paths: " + Matisse.obtainPathResult(data))
            Timber.d("Use the selected photos with original: " + (Matisse.obtainOriginalState(data)))
        }
    }

    /**
     * 检查权限
     * @Author:  XuZhenHui
     * @Time:  2021/11/9 14:26
     */
    private fun checkPermissions(): Boolean {
        val rxPermissions = RxPermissions(this)
        return rxPermissions.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)&&
                rxPermissions.isGranted(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

}