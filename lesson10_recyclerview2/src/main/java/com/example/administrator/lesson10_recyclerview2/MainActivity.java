package com.example.administrator.lesson10_recyclerview2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;

    List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv);
        for (int i = 0; i < 1000; i++) {
            mList.add("条目" + i);
        }
        final MyAdapter adapter = new MyAdapter(this, mList);
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getBaseContext(), "点击的是" + position + "的itemClick", Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemLongClickListener(new MyAdapter.OnItemLongClickListener() {
            @Override
            public void onLongClick(int position) {
                //长按删除数据
                mList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        rv.setAdapter(adapter);

        //布局管理器
        rv.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));
    }


    public static class VH extends RecyclerView.ViewHolder {
        TextView tv;

        public VH(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(android.R.id.text1);
            tv.setBackgroundResource(R.drawable.bg);
        }
    }


}
