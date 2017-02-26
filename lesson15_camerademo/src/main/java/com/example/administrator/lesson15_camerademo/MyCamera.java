package com.example.administrator.lesson15_camerademo;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Administrator on 2016/11/3.
 */

public class MyCamera extends SurfaceView implements SurfaceHolder.Callback {


    static Camera camera;

    public MyCamera(Context context) {
        super(context);
        init();
    }


    private void init() {
        getHolder().addCallback(this);
    }

    public MyCamera(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public void surfaceCreated(final SurfaceHolder holder) {
        release();
        open(holder);

    }

    private void open(SurfaceHolder holder) {
        camera = Camera.open();
        try {
            camera.setPreviewDisplay(holder);
            camera.setDisplayOrientation(90);
            //模拟器需要设置
            Camera.Parameters parameters = camera.getParameters();
            parameters.setPictureSize(300, 300);
            camera.setParameters(parameters);
            camera.startPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    public void start() {
        if (camera != null)
            camera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        release();
    }

    public void release() {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }
}
