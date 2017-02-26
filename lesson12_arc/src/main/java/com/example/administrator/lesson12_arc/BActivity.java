package com.example.administrator.lesson12_arc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */

public class BActivity extends AppCompatActivity {

    PullToRefreshListView lv;

    List<String> mList = new ArrayList<>();

    ArrayAdapter<String> adadpter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        for (int i = 0; i < 50; i++) {
            mList.add("数据" + i);
        }
        lv = (PullToRefreshListView) findViewById(R.id.lv);
        //如果需要支持上拉
        lv.setMode(PullToRefreshBase.Mode.BOTH);
        adadpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mList);
        lv.setAdapter(adadpter);
//        ListView lvs ;
//        lvs.setSelection(100);
        //设置监听
        //只要设置了监听，那么进度条就不会再关闭,必须在请求数据完毕后 手动关闭
        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉
                mList.clear();
                refresh();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //上拉
                addData();
            }
        });
    }


    //添加数据
    public void addData() {
        new Thread() {
            @Override
            public void run() {
                index++;
                for (int i = 0; i < 50; i++) {
                    mList.add("新加载" + i * index);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adadpter.notifyDataSetChanged();
                        lv.onRefreshComplete();
                    }
                });
            }
        }.start();

    }

    int index = 1;

    //模拟刷新

    public void refresh() {
        new Thread() {
            @Override
            public void run() {
                index++;
                for (int i = 0; i < 50; i++) {
                    mList.add("数据" + i * index);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adadpter.notifyDataSetChanged();
                        }
                    });
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //关闭进度条
                        lv.onRefreshComplete();
                    }
                });
            }
        }.start();
    }
}
