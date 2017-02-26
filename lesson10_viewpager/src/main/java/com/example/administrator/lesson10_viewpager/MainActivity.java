package com.example.administrator.lesson10_viewpage;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TabLayout tablayout;


    ViewPager vp;

    Timer timer;
    LinearLayout points;

    public void start() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        vp.setCurrentItem(vp.getCurrentItem() + 1);
                    }
                });
                //修改UI了
            }
        }, 2000, 2000);
    }

    public void stop() {
        if (timer != null)
            timer.cancel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        vp = (ViewPager) findViewById(R.id.vp);
        vp.setAdapter(new MyPagerAdapter());
        //tablayout也需要在设置适配器之后
        tablayout.setupWithViewPager(vp);


        //在设置适配器之后
        // vp.setCurrentItem(Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % 4));
        //start();
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //无限页
            }

            int index; //一开始是第0页 已经被选中

            //当页面选择时
            @Override
            public void onPageSelected(int position) {
                // Toast.makeText(getBaseContext(), position + "页", Toast.LENGTH_SHORT).show();
                points.getChildAt(index).setSelected(false);
                points.getChildAt(position % 4).setSelected(true);
                index = position % 4;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //状态改变
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    //停止自动滚动
                } else {
                    //开始滚动
                }
            }
        });

        initPoint();
    }

    private void initPoint() {
        points = (LinearLayout) findViewById(R.id.points);
        for (int i = 0; i < 4; i++) {
            View view = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            view.setBackgroundResource(R.drawable.point_selector);
            params.setMargins(10, 10, 10, 10);
            view.setLayoutParams(params);
            points.addView(view);
        }
        //第0个先被选中
        points.getChildAt(0).setSelected(true);
    }


    //适配器
    class MyPagerAdapter extends PagerAdapter {
        //每一个Viewpager的一页就是一个页面
        List<View> viewList = new ArrayList<>();

        int[] imgId = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d};

        public MyPagerAdapter() {
            for (int i = 0; i < 4; i++) {
                ImageView iv = new ImageView(getBaseContext());
                iv.setImageResource(imgId[i]);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                viewList.add(iv);
            }
        }

        @Override
        public int getCount() {
            return viewList.size();//232
        }

        //判断两页是否相同，地址
        @Override
        public boolean isViewFromObject(View view, Object object) {
            //google要求的写法
            return view == object;
        }


        //必须加入 下面两个方法

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //添加一个item 显示
            container.addView(viewList.get(position % 4));
            return viewList.get(position % 4);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position % 4));
        }


        String[] titles = {"第一页", "第二页", "第三页", "第四页"};

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position % 4];
        }
    }
}
