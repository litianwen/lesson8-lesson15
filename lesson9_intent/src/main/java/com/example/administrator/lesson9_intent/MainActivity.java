package com.example.administrator.lesson9_intent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void action(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //ActivityNotFound
        startActivity(intent);
    }

    public void data(View v) {
        //查看
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.baidu.com"));
        startActivity(intent);
    }

    public void data2(View v) {
        //查看
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(""));
        intent.setData(Uri.parse("tel:13911541211"));
        //6.0以上的sdk
        //android  动态权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //重新请求权限
            return;
        }
        startActivity(intent);
    }

    public void type(View v) {
        //分享
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "fbb.jpg")), "image/*");
        intent.setData(Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "fbb.jpg")));
        intent.setType("image/*");
        startActivity(intent);
    }
}
