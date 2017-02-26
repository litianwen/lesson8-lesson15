package com.example.administrator.lesson10_viewpager_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager vp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = (ViewPager) findViewById(R.id.vp);
        mList.add(new CallFragment());
        mList.add(new ChatFragment());
        //必须使用v4包的管理器
        vp.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
    }

    //数据
    List<Fragment> mList = new ArrayList<>();

    //FragmentPagerAdapter 用来切换Fragment
    //构造方法 必须要一个管理器
//    private FragmentPagerAdapter adapter = new FragmentPagerAdapter() {
//        @Override
//        public Fragment getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public int getCount() {
//            return 0;
//        }
//    };

    //必须使用类
    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

    }

}
