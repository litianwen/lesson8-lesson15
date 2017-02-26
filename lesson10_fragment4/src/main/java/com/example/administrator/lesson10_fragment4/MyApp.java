package com.example.administrator.lesson10_fragment4;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */

/**
 * 注册之后，系统帮我们生成唯一的一个Application
 */
public class MyApp extends Application {

    private static MyApp instance;

    public static MyApp getInstance() {
        return instance;
    }

    List<Activity> activityList = new ArrayList<>();

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    /**
     * 一定不允许私有化
     */
//    private MyApp() {
//
//    }

    //java
    //私有化构造方法
    //创建本类静态实例
    //创建外部访问的静态方法 返回静态实例
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Log.e("TAG", "-----------------------MyApp创建");
    }


    //全局对象 User!=null

    //循环遍历退出  记录所有打开的Activity，想到在任意一个地方退出程序，循环遍历所有的activity并且调用finish

    //共享数据
    //Setting 夜间模式，白天模式
}
