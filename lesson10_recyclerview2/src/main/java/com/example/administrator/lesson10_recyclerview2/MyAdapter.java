package com.example.administrator.lesson10_recyclerview2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/10/24.
 */


public class MyAdapter extends RecyclerView.Adapter<MainActivity.VH> {

    Context mContext;
    List<String> mList;

    public MyAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public MainActivity.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainActivity.VH(View.inflate(mContext, android.R.layout.simple_list_item_1, null));
    }

    @Override
    public void onBindViewHolder(MainActivity.VH holder, final int position) {
        holder.tv.setText(mList.get(position));

        //自己做item点击
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onClick(position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemLongClickListener != null)
                    onItemLongClickListener.onLongClick(position);
                //返回false会在长安结束后继续点击
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    /**
     * 点击
     */
    public interface OnItemClickListener {
        void onClick(int position);
    }

    OnItemClickListener onItemClickListener;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemLongClickListener {
        void onLongClick(int position);
    }

    OnItemLongClickListener onItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}
