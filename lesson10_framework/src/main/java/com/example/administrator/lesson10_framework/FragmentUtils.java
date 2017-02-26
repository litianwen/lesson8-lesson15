package com.example.administrator.lesson10_framework;

import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2016/10/24.
 */

public class FragmentUtils {

    //工厂模式
    public static final Fragment newInstance(int type) {
        switch (type) {
            case 0:
                return new FavorFragment();
            case 1:
                return new OrderFragment();
            case 2:
                return new HomeFragment();
            case 3:
                return new UCFragment();
            case 4:
                return new SettingFragment();
        }
        return null;
    }


    //反射方式
    public static final String[] className = {"Favor", "Order", "Home", "UC", "Setting"};

    public static final Fragment getInstance(int type) {
        try {
            //通过一个类的字符串对象，转换成一个Class 类类型
            Class clasName = Class.forName("com.example.administrator.lesson10_framework." + className[type] + "Fragment");
            //通过类的类型去创建实例  必须有无参构造方法 并且是公开的
            return (Fragment) clasName.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
