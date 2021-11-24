package com.asuka.androidopensourcelibrarystudydemo.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityCameraViewBinding;
import com.bumptech.glide.Glide;
import com.otaliastudios.cameraview.BitmapCallback;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.PictureResult;

import org.jetbrains.annotations.NotNull;

public class CameraViewActivity extends AppCompatActivity {
    private Context mContext = this;
    ActivityCameraViewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCameraViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_camera);
        binding.cameraView.setLifecycleOwner(this);
        binding.takePhotoBtn.setOnClickListener(view -> {
            binding.cameraView.takePicture();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.cameraView.addCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(@NonNull @NotNull PictureResult result) {
                if (result!=null){
                    result.toBitmap(new BitmapCallback() {
                        @Override
                        public void onBitmapReady(@Nullable @org.jetbrains.annotations.Nullable Bitmap bitmap) {
                            Glide.with(mContext)
                                    .load(bitmap)
                                    .into(binding.imageView);
                        }
                    });

                }
            }
        });
    }
}