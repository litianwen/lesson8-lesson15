package com.example.administrator.lesson12_bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Bitmap bmp;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bmp = Bitmap.createBitmap(200, 200, Bitmap.Config.RGB_565);
        iv = (ImageView) findViewById(R.id.iv);
        //200*200 = 40000 = 40b 1024
        iv.setImageBitmap(bmp);
    }

    public void save(View v) {
        //jpg没有透明度
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/aaa.png";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fromFile(View v) {
        //需要读取权限
        String fileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/fbb.jpg";
        Bitmap bmp = BitmapFactory.decodeFile(fileName);
        iv.setImageBitmap(bmp);

    }

    //使用Image框架
    public void fromStream(View v) {
        //从网络
        //域名
        new Thread() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("https://img.alicdn.com/tps/TB1l033MVXXXXbrXVXXXXXXXXXX-520-280.jpg");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    //1.子线程无法刷新UI
                    //2.主线程无法请求网络
                    InputStream is = url.openStream();
                    final Bitmap bmp = BitmapFactory.decodeStream(is);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            iv.setImageBitmap(bmp);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }
}


