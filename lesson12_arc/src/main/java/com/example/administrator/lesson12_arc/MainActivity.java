package com.example.administrator.lesson12_arc;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SwipeMenuListView smlv;

    List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 50; i++) {
            mList.add("数据" + i);
        }
        smlv = (SwipeMenuListView) findViewById(R.id.smlv);
        // final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mList);
        smlv.setAdapter(adapter);
        smlv.setMenuCreator(menu);
        smlv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                //position 条目
                //index  第几个按钮
                switch (menu.getViewType()) {
                    case 0:
                        //
                        mList.remove(position);
                        adapter.notifyDataSetChanged();
                        break;
                    case 1:
                        mList.add(0, mList.remove(position));
                        adapter.notifyDataSetChanged();
                        break;
                }
//                switch (index) {
//                    case 0:
//                        mList.remove(position);
//                        adapter.notifyDataSetChanged();
//                        break;
//                    case 1:
//                        mList.add(0, mList.remove(position));
//                        adapter.notifyDataSetChanged();
//                        break;
//
//                }
                return false;
            }
        });
    }

    //创建菜单
    SwipeMenuCreator menu = new SwipeMenuCreator() {
        @Override
        public void create(SwipeMenu menu) {
            switch (menu.getViewType()) {
                case 0:
                    SwipeMenuItem item = new SwipeMenuItem(MainActivity.this);
                    item.setBackground(android.R.color.darker_gray);
                    item.setTitle("删除");
                    item.setTitleSize(14);
                    item.setTitleColor(Color.WHITE);
                    //必须设置
                    item.setWidth(90);
                    menu.addMenuItem(item);
                    break;
                case 1:
                    SwipeMenuItem item2 = new SwipeMenuItem(MainActivity.this);
                    item2.setBackground(android.R.color.holo_blue_bright);
                    item2.setTitle("置顶");
                    item2.setTitleSize(14);
                    item2.setTitleColor(Color.WHITE);
                    //必须设置
                    item2.setWidth(90);
                    menu.addMenuItem(item2);
                    break;
                case 2:
                    SwipeMenuItem item3 = new SwipeMenuItem(MainActivity.this);
                    item3.setBackground(android.R.color.darker_gray);
                    item3.setIcon(R.drawable.ic_launcher);
                    item3.setTitleSize(14);
                    item3.setTitleColor(Color.WHITE);
                    //必须设置
                    item3.setWidth(90);
                    menu.addMenuItem(item3);
                    break;
            }
//            SwipeMenuItem item = new SwipeMenuItem(MainActivity.this);
//            item.setBackground(android.R.color.darker_gray);
//            item.setTitle("删除");
//            item.setTitleSize(14);
//            item.setTitleColor(Color.WHITE);
//            //必须设置
//            item.setWidth(90);
//            menu.addMenuItem(item);
//
//            SwipeMenuItem item2 = new SwipeMenuItem(MainActivity.this);
//            item2.setBackground(android.R.color.holo_blue_bright);
//            item2.setTitle("置顶");
//            item2.setTitleSize(14);
//            item2.setTitleColor(Color.WHITE);
//            //必须设置
//            item2.setWidth(90);
//            menu.addMenuItem(item2);
//            SwipeMenuItem item3 = new SwipeMenuItem(MainActivity.this);
//            item3.setBackground(android.R.color.darker_gray);
//            item3.setIcon(R.drawable.ic_launcher);
//            item3.setTitleSize(14);
//            item3.setTitleColor(Color.WHITE);
//            //必须设置
//            item3.setWidth(90);
//            menu.addMenuItem(item3);
        }
    };


    BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //switch (getItemViewType(position))


            if (convertView == null)
                convertView = View.inflate(MainActivity.this, android.R.layout.simple_list_item_1, null);
            TextView tv = (TextView) convertView;
            tv.setText(mList.get(position));
            return convertView;
        }


        //条目有多少个类型
        @Override
        public int getViewTypeCount() {
            return 3;
        }


        //对象里面
        //聊天记录 谁发的
        @Override
        public int getItemViewType(int position) {
            return position % 3; //User.getLeft
        }
    };
}
