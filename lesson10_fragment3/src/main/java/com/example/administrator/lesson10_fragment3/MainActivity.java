package com.example.administrator.lesson10_fragment3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {


    Fragment[] fragments = new Fragment[4];

    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg = (RadioGroup) findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(this);
        showFragment(0);
        rg.check(R.id.rb1);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        group.getChildAt(checkedId).getTag();
        switch (checkedId) {
            case R.id.rb1:
                showFragment(0);
                break;
            case R.id.rb2:
                showFragment(1);
                break;
            case R.id.rb3:
                showFragment(2);
                break;
            case R.id.rb4:
                showFragment(3);
                break;
        }
    }

    //上一次界面上显示的哪一个Fragment
    int currIndex = -1; //代表第一次进来

    //展示Fragment
    public void showFragment(int index) {
        //当前已经显示了这个界面
        if (index == currIndex)
            return;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //如果用户已经打开过其中一个Fragment，在切换的时候，我需要将上一次的移除
        if (currIndex != -1) {
            //直接移除
            ft.hide(fragments[currIndex]);
        }
        //显示新的Fragment
        if (fragments[index] == null) {
            //创建操作
            createFragment(index);
            //添加进去
            ft.add(R.id.fl_content, fragments[index]);
        } else {
            //不是空的
            ft.show(fragments[index]);
        }
        currIndex = index;
        ft.commit();
    }

    //新建一个Fragment
    private void createFragment(int index) {
        switch (index) {
            case 0:
                fragments[0] = new ManagerFragment();
                break;
            case 1:
                fragments[1] = new ContactsFragment();
                break;
            case 2:
                fragments[2] = new NewsFragment();
                break;
            case 3:
                fragments[3] = new SettingFragment();
                break;
        }
    }
}
