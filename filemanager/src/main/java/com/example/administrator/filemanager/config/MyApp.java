package com.example.administrator.filemanager.config;

import android.app.Application;

import com.example.administrator.mylib.utils.FileUtils;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2016/11/4.
 */

public class MyApp extends Application {

    //循环遍历退出...
    //共享数据
    //单例模式

    //私有静态本类对象
    private static MyApp instance;


    public static MyApp getInstance() {
        return instance;
    }


    private List<File> allFiles;


    public List<File> getAllFiles() {
        //如果返回空，代表还没有加载完毕
        return allFiles;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        //开启线程来获取所有文件
        instance = this;
        down();
    }

    public void down() {
        new Thread() {
            @Override
            public void run() {
                allFiles = FileUtils.getAllFile();
            }
        }.start();
    }


}
