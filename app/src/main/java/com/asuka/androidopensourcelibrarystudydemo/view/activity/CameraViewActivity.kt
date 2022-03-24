package com.asuka.androidopensourcelibrarystudydemo.view.activity

import android.content.Context
import android.graphics.*
import android.os.Bundle
import com.asuka.androidopensourcelibrarystudydemo.utils.Base64BitmapUtil
import com.bumptech.glide.Glide
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.PictureResult
import com.otaliastudios.cameraview.BitmapCallback
import timber.log.Timber
import android.os.Build
import android.media.Image
import android.renderscript.*
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityCameraViewBinding
import com.otaliastudios.cameraview.controls.Engine
import com.otaliastudios.cameraview.frame.Frame
import com.otaliastudios.cameraview.frame.FrameProcessor
import java.io.ByteArrayOutputStream
import java.lang.Exception

class CameraViewActivity : AppCompatActivity(), FrameProcessor {
    private val mContext: Context = this
    private var mBitmap1: Bitmap? = null
    private var mBitmap2: Bitmap? = null
    private var base64Pic: String? = null
    var binding: ActivityCameraViewBinding? = null

    //用于将nv21数据转为bitmap
    //https://source.android.google.cn/devices/architecture/vndk/renderscript?hl=zh-cn
    //RenderScript 是用于在 Android 上以高性能运行计算密集型任务的框架。
    //RenderScript 专为数据并行计算而设计，不过串行工作负载也可以从中受益。
    //RenderScript 运行时可以并行安排设备上可用的多个处理器（如多核 CPU 和 GPU）上的工作负载，
    //使开发者能够专注于表达算法而不是调度工作。
    //RenderScript 对于专注于图像处理、计算摄影或计算机视觉的应用来说尤其有用。
    //注意：从 Android 12 开始，RenderScript API 已被弃用。它们将继续正常运行，但 AOSP 预计设备和组件制造商会逐渐停止提供硬件加速支持。
    var renderScript: RenderScript? = null
    var scriptIntrinsicYuvToRGB: ScriptIntrinsicYuvToRGB? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraViewBinding.inflate(
            layoutInflater
        )
        //        setContentView(R.layout.activity_camera);
        setContentView(binding!!.root)
        renderScript = RenderScript.create(this)
        scriptIntrinsicYuvToRGB =
            ScriptIntrinsicYuvToRGB.create(renderScript, Element.U8_4(renderScript))

        //为CameraView设置生命周期所有者，不需要手动打开或者关闭
        binding!!.cameraView.setLifecycleOwner(this)
        //Camera引擎 1或2
        // 1生成预览帧编码为ImageFormat.NV21
        // 2生成预览帧默认编码ImageFormat.YUV_420_888.可以设置成 cameraView.setFrameProcessingFormat(ImageFormat.YUV_422_888);
        // 能否使用Camera2需要设备支持
        binding!!.cameraView.engine = Engine.CAMERA2
        //添加帧处理器，实现类实现FrameProcessor接口
//        binding.cameraView.addFrameProcessor(this);

        //点击拍照
        binding!!.takePhotoBtn.setOnClickListener { view: View? -> binding!!.cameraView.takePicture() }
        //打开摄像头
        binding!!.openCameraBtn.setOnClickListener { view: View? ->
//            binding.cameraView.open();
            base64Pic = Base64BitmapUtil.bitmapToBase64(mBitmap1)
            Log.d("base64", "onCreate: $base64Pic")
        }
        //关闭摄像头
        binding!!.closeCameraBtn.setOnClickListener { view: View? ->
//           binding.cameraView.close();
            mBitmap2 = Base64BitmapUtil.base64ToBitmap(base64Pic)
            Glide.with(mContext)
                .load(mBitmap2)
                .into(binding!!.imageView2)
            binding!!.cameraView.close()
            binding!!.imageView2.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        //addCameraListener 为cameraView设置事件监听处理
        binding!!.imageView2.visibility = View.GONE
        binding!!.cameraView.addCameraListener(object : CameraListener() {
            override fun onPictureTaken(result: PictureResult) {
                if (result != null) {
                    result.toBitmap { bitmap ->
                        mBitmap1 = bitmap
                        //                            Glide.with(mContext)
//                                    .load(bitmap)
//                                    .into(binding.imageView2);
//                            binding.cameraView.close();
//                            binding.imageView2.setVisibility(View.VISIBLE);
                    }
                }
            }
        })
    }

    /**
     * <pre>
     * desc:   预览帧处理,相机预览界面生成的预览帧在这里处理
     * Author:  XuZhenHui
     * Time:  2021/11/26 8:43
    </pre> *
     */
    override fun process(frame: Frame) {
//     从frame.getData()方法中获取到的数据会因为使用的CameraApi不同而不同，默认使用Camera1,将得到Nv21格式的数组数据
        Thread {
            if (frame.dataClass == ByteArray::class.java) {
                val bitmap = getBitmapFromFrameData(
                    renderScript,
                    scriptIntrinsicYuvToRGB,
                    frame.getData(),
                    frame.size.width,
                    frame.size.height
                )
                if (bitmap != null) {
                    Timber.d("转换成功")
                    runOnUiThread {
                        Glide.with(mContext)
                            .load(bitmap)
                            .into(binding!!.imageView)
                    }
                }
            } else if (frame.dataClass == Image::class.java) {
                Timber.d("有图像image")
            }
        }.run()
    }

    /**
     * <pre>
     * desc:   将相机预览帧得到的Nv21格式数据转换成bitmap。
     * Time:  2021/11/26 10:10
     * @param data Nv21格式的数据数据
    </pre> *
     */
    // TODO: 2021/11/26  此算法已过时，应迁移
    fun getBitmapFromFrameData(
        rs: RenderScript?,
        yuvToRgbIntrinsic: ScriptIntrinsicYuvToRGB?,
        data: ByteArray,
        width: Int,
        height: Int
    ): Bitmap? {
        var yuvType: Type.Builder? = null
        val rgbaType: Type.Builder
        var `in`: Allocation? = null
        var out: Allocation? = null
        return try {
            val bitmap: Bitmap
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                yuvType = Type.Builder(rs, Element.U8(rs)).setX(data.size)
                `in` = Allocation.createTyped(rs, yuvType.create(), Allocation.USAGE_SCRIPT)
                rgbaType = Type.Builder(rs, Element.RGBA_8888(rs)).setX(
                    width
                ).setY(height)
                out = Allocation.createTyped(rs, rgbaType.create(), Allocation.USAGE_SCRIPT)
                `in`.copyFrom(data)
                yuvToRgbIntrinsic!!.setInput(`in`)
                yuvToRgbIntrinsic.forEach(out)
                bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                out.copyTo(bitmap)
            } else {
                val baos: ByteArrayOutputStream
                val rawImage: ByteArray
                //处理data
                val newOpts = BitmapFactory.Options()
                newOpts.inJustDecodeBounds = true
                val yuvimage = YuvImage(data, ImageFormat.NV21, width, height, null)
                baos = ByteArrayOutputStream()
                yuvimage.compressToJpeg(
                    Rect(0, 0, width, height),
                    100,
                    baos
                ) // 80--JPG图片的质量[0-100],100最高
                rawImage = baos.toByteArray()
                //将rawImage转换成bitmap
                val options = BitmapFactory.Options()
                options.inPreferredConfig = Bitmap.Config.RGB_565
                bitmap = BitmapFactory.decodeByteArray(rawImage, 0, rawImage.size, options)
            }
            bitmap
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}