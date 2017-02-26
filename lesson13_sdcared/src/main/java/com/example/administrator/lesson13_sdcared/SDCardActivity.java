package com.example.administrator.lesson13_sdcared;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */

public class SDCardActivity extends AppCompatActivity {

    ArrayList<File> fileList = new ArrayList();

    //权限
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button btn = new Button(this);
        setContentView(btn);
        btn.setText("读SD");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        getAllFiles(Environment.getExternalStorageDirectory(), fileList);
                        System.out.println(fileList.toString());
                    }
                }.start();
            }
        });
        try {
            FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + "/aaa.txt");
            fos.write("你好，外部存储器".getBytes());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getAllFiles(File dir, List<File> fileList) {
        File[] files = dir.listFiles();
        if (files == null)
            return;

        for (File file : files) {
            if (file.isDirectory())
                getAllFiles(file, fileList);
            fileList.add(file);
        }
    }

}
