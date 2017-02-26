package com.example.administrator.lesson15_camera;

import android.Manifest;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.FileOutputStream;
import java.io.IOException;

import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    Camera camera;
    SurfaceView sv;


//    CameraManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //以这种方法打开的  如果被占用，那么抛异常
        sv = (SurfaceView) findViewById(R.id.sv);
        sv.getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        //6.0动态权限
        if (Build.VERSION.SDK_INT >= 23) {
            //Manifest.permission.CAMERA
            //如果用户并没有同意该权限
            if (checkSelfPermission(Manifest.permission.CAMERA) != PERMISSION_GRANTED) {
                //申请权限
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
            } else {
                open(holder);
            }
        } else {
            open(holder);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //如果用户同意了我的权限
        if (requestCode == 100 && grantResults[0] == PERMISSION_GRANTED) {
            open(sv.getHolder());
        }
    }

    public void open(SurfaceHolder holder) {
        //获取摄像头
        camera = Camera.open();
        //默认方向是-90度倾斜
        camera.setDisplayOrientation(90);
        try {
            camera.setPreviewDisplay(holder);
//            Camera.Parameters parameters = camera.getParameters();
//            parameters.setPictureSize(720, 1080);
//            camera.setParameters(parameters);
        } catch (IOException e) {
            e.printStackTrace();
        }
        camera.startPreview();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void paizhao(View v) {
        camera.takePicture(null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                //     synchronized (MainActivity.this.camera) {
                try {
                    FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + "/cam.jpg");
                    fos.write(data);
                    fos.flush();
                    fos.close();
                    camera.startPreview();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // }
        });
    }
}
