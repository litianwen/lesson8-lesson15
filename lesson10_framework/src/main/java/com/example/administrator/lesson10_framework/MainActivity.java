package com.example.administrator.lesson10_framework;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    RadioGroup tab_rg;

    Fragment[] fragments = new Fragment[5];

    int currIndex = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tab_rg = (RadioGroup) findViewById(R.id.tab_rg);
        tab_rg.setOnCheckedChangeListener(this);
        showFragment(2);
        tab_rg.check(R.id.tab_rb_home);
    }


    //显示Fragment
    private void showFragment(int index) {
        //在ImageView的框架中使用
        if (index == currIndex)
            return;

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (currIndex != -1) {
            //隐藏上一个页面
            ft.hide(fragments[currIndex]);
        }

        if (fragments[index] == null) {
            //创建
            fragments[index] = FragmentUtils.getInstance(index);
            //添加进去
            ft.add(R.id.fl_content, fragments[index]);
        } else {
            ft.show(fragments[index]);
        }
        ft.commit();
        currIndex = index;

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        View rg = tab_rg.findViewById(checkedId);//R.id.tab_rb_home;
        String tag = rg.getTag().toString();
        showFragment(Integer.parseInt(tag));
    }
}
