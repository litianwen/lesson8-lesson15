package com.example.administrator.lesson10_recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    List<Img> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        mList.add(new Img(R.mipmap.a, "aaa"));
        mList.add(new Img(R.mipmap.abc, "bcabc"));
        mList.add(new Img(R.mipmap.b, "abbbaa"));
        mList.add(new Img(R.mipmap.bg, "bgbg"));
        mList.add(new Img(R.mipmap.c, "cccc"));
        mList.add(new Img(R.mipmap.cbd, "asdf"));
        mList.add(new Img(R.mipmap.d, "bsadccc"));
        mList.add(new Img(R.mipmap.fbb, "fbb"));
        mList.add(new Img(R.mipmap.lichen, "李晨"));
        mList.add(new Img(R.mipmap.wangbaoqiang, "王宝强"));
        recyclerView.setAdapter(new MyAdapter());
        //没有布局管理器
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL));
    }

    //布局的内容组件
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView iv;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }


    //适配器
    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {


        /**
         * @param parent
         * @param viewType
         * @return 当一个页面没有加载完毕的情况，会持续的调用该方法
         */

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //创建ViewHolder
            //导入布局
            View layout = View.inflate(getBaseContext(), R.layout.item_layout, null);
            MyViewHolder viewHolder = new MyViewHolder(layout);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            //为每一布局设置属性
            //holder 布局组件
            //position 当前第几项 list.get(position);
            Img img = mList.get(position);
            holder.tv.setText(img.text);
            holder.iv.setImageResource(img.imgId);
        }

        @Override
        public int getItemCount() {
            //有多少个item
            return mList.size();
        }
    }


    //数据
    class Img {
        int imgId;
        String text;

        public Img(int imgId, String text) {
            this.imgId = imgId;
            this.text = text;
        }
    }
}
