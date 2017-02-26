package com.example.administrator.lesson10_fragment2;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Eclipse中 需要修改成FragmentActivity
 */
public class MainActivity extends AppCompatActivity {
    DongTaiFragment fragment;
    DongTaiFragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //在代码中 动态的添加和移除Fragment
        fragment = new DongTaiFragment();
        fragment2 = new DongTaiFragment2();
    }

    public void add(View v) {
        //动态添加
        FragmentManager manager = getSupportFragmentManager();
        //manager中可以找到当前activity上所有的fragment
//        manager.findFragmentById() 或者byTag
        //打开一个事务管理器
        //开始一个事务

        FragmentTransaction ft = manager.beginTransaction();
        //执行一系列的指令
        ft.add(R.id.fl_content, fragment);
        //提交事务才开始执行
        ft.commit();
    }

    public void remove(View v) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }

    public void replace(View v) {
        //相当于 移除与add一体
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_content, fragment2);
        ft.commit();
    }


    public void attach(View v) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.attach(fragment);
        ft.commit();
    }

    public void detach(View v) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.detach(fragment);
        ft.commit();
    }

    public void hide(View v) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(fragment);
        ft.commit();
    }

    public void show(View v) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.show(fragment);
        ft.commit();
    }
}
