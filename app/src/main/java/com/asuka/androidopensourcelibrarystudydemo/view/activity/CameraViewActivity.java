package com.asuka.androidopensourcelibrarystudydemo.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.renderscript.Type;
import android.util.Log;
import android.view.View;

import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityCameraViewBinding;
import com.asuka.androidopensourcelibrarystudydemo.utils.Base64BitmapUtil;
import com.bumptech.glide.Glide;
import com.otaliastudios.cameraview.BitmapCallback;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.PictureResult;
import com.otaliastudios.cameraview.controls.Engine;
import com.otaliastudios.cameraview.frame.Frame;
import com.otaliastudios.cameraview.frame.FrameProcessor;
import org.jetbrains.annotations.NotNull;
import java.io.ByteArrayOutputStream;

import timber.log.Timber;


public class CameraViewActivity extends AppCompatActivity implements FrameProcessor {
    private Context mContext = this;
    private Bitmap mBitmap1;
    private Bitmap mBitmap2;
    private String base64Pic;
    ActivityCameraViewBinding binding;

    //用于将nv21数据转为bitmap
    //https://source.android.google.cn/devices/architecture/vndk/renderscript?hl=zh-cn
    //RenderScript 是用于在 Android 上以高性能运行计算密集型任务的框架。
    //RenderScript 专为数据并行计算而设计，不过串行工作负载也可以从中受益。
    //RenderScript 运行时可以并行安排设备上可用的多个处理器（如多核 CPU 和 GPU）上的工作负载，
    //使开发者能够专注于表达算法而不是调度工作。
    //RenderScript 对于专注于图像处理、计算摄影或计算机视觉的应用来说尤其有用。
    //注意：从 Android 12 开始，RenderScript API 已被弃用。它们将继续正常运行，但 AOSP 预计设备和组件制造商会逐渐停止提供硬件加速支持。
    RenderScript renderScript = null;
    ScriptIntrinsicYuvToRGB scriptIntrinsicYuvToRGB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCameraViewBinding.inflate(getLayoutInflater());
        //        setContentView(R.layout.activity_camera);
        setContentView(binding.getRoot());

        renderScript = RenderScript.create(this);
        scriptIntrinsicYuvToRGB = ScriptIntrinsicYuvToRGB.create(renderScript, Element.U8_4(renderScript));

        //为CameraView设置生命周期所有者，不需要手动打开或者关闭
        binding.cameraView.setLifecycleOwner(this);
        //Camera引擎 1或2
        // 1生成预览帧编码为ImageFormat.NV21
        // 2生成预览帧默认编码ImageFormat.YUV_420_888.可以设置成 cameraView.setFrameProcessingFormat(ImageFormat.YUV_422_888);
        // 能否使用Camera2需要设备支持
        binding.cameraView.setEngine(Engine.CAMERA2);
        //添加帧处理器，实现类实现FrameProcessor接口
//        binding.cameraView.addFrameProcessor(this);

        //点击拍照
        binding.takePhotoBtn.setOnClickListener(view -> {
            binding.cameraView.takePicture();
        });
        //打开摄像头
        binding.openCameraBtn.setOnClickListener(view -> {
//            binding.cameraView.open();
            base64Pic = Base64BitmapUtil.bitmapToBase64(mBitmap1);
            Log.d("base64", "onCreate: "+base64Pic);
        });
        //关闭摄像头
        binding.closeCameraBtn.setOnClickListener(view ->{
//           binding.cameraView.close();
            mBitmap2 = Base64BitmapUtil.base64ToBitmap(base64Pic);
                                        Glide.with(mContext)
                                    .load(mBitmap2)
                                    .into(binding.imageView2);
                            binding.cameraView.close();
                            binding.imageView2.setVisibility(View.VISIBLE);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //addCameraListener 为cameraView设置事件监听处理
        binding.imageView2.setVisibility(View.GONE);
        binding.cameraView.addCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(@NonNull @NotNull PictureResult result) {
                if (result!=null){
                    result.toBitmap(new BitmapCallback() {
                        @Override
                        public void onBitmapReady(@Nullable @org.jetbrains.annotations.Nullable Bitmap bitmap) {
                            mBitmap1=bitmap;
//                            Glide.with(mContext)
//                                    .load(bitmap)
//                                    .into(binding.imageView2);
//                            binding.cameraView.close();
//                            binding.imageView2.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }
        });
    }

    /**
     *  <pre>
     *  desc:   预览帧处理,相机预览界面生成的预览帧在这里处理
     *  Author:  XuZhenHui
     *  Time:  2021/11/26 8:43
     *  </pre>
     */
    @Override
    public void process(@NonNull @NotNull Frame frame) {
//     从frame.getData()方法中获取到的数据会因为使用的CameraApi不同而不同，默认使用Camera1,将得到Nv21格式的数组数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                    if (frame.getDataClass()==byte[].class){
                        Bitmap bitmap = getBitmapFromFrameData(renderScript, scriptIntrinsicYuvToRGB, frame.getData(), frame.getSize().getWidth(), frame.getSize().getHeight());
                        if (bitmap!=null){
                            Timber.d("转换成功");
                            runOnUiThread(() -> {
                                Glide.with(mContext)
                                        .load(bitmap)
                                        .into(binding.imageView);
                            });
                        }
                    }else if (frame.getDataClass()== Image.class){
                        Timber.d("有图像image");
                    }
            }
        }).run();
    }


    /**
     *  <pre>
     *  desc:   将相机预览帧得到的Nv21格式数据转换成bitmap。
     *  Time:  2021/11/26 10:10
     * @param data Nv21格式的数据数据
     *  </pre>
     */
    // TODO: 2021/11/26  此算法已过时，应迁移
    public Bitmap getBitmapFromFrameData(RenderScript rs, ScriptIntrinsicYuvToRGB yuvToRgbIntrinsic, byte[] data, int width, int height) {
        Type.Builder yuvType = null, rgbaType;
        Allocation in = null, out = null;
        try {
            final int w = width;  //宽度
            final int h = height;
            Bitmap bitmap;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                yuvType = new Type.Builder(rs, Element.U8(rs)).setX(data.length);
                in = Allocation.createTyped(rs, yuvType.create(), Allocation.USAGE_SCRIPT);
                rgbaType = new Type.Builder(rs, Element.RGBA_8888(rs)).setX(w).setY(h);
                out = Allocation.createTyped(rs, rgbaType.create(), Allocation.USAGE_SCRIPT);
                in.copyFrom(data);
                yuvToRgbIntrinsic.setInput(in);
                yuvToRgbIntrinsic.forEach(out);
                bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                out.copyTo(bitmap);
            } else {
                ByteArrayOutputStream baos;
                byte[] rawImage;
                //处理data
                BitmapFactory.Options newOpts = new BitmapFactory.Options();
                newOpts.inJustDecodeBounds = true;
                YuvImage yuvimage = new YuvImage(data, ImageFormat.NV21, w, h, null);
                baos = new ByteArrayOutputStream();
                yuvimage.compressToJpeg(new Rect(0, 0, w, h), 100, baos);// 80--JPG图片的质量[0-100],100最高
                rawImage = baos.toByteArray();
                //将rawImage转换成bitmap
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                bitmap = BitmapFactory.decodeByteArray(rawImage, 0, rawImage.length, options);
            }
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}