package com.example.administrator.filemanager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.filemanager.utils.ConstantUtils;
import com.example.administrator.filemanager.R;
import com.example.administrator.mylib.adapter.ListItemAdapter;

/**
 * Created by Administrator on 2016/11/4.
 */

public class GridAdapter extends ListItemAdapter<String> {


    public GridAdapter(Context context, String[] list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.grid_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        holder.iv.setImageResource(ConstantUtils.resId[position]);
        holder.tv.setText(getItem(position));
        return convertView;
    }

    class ViewHolder {
        TextView tv;
        ImageView iv;

        public ViewHolder(View convertView) {
            tv = (TextView) convertView.findViewById(R.id.tv);
            iv = (ImageView) convertView.findViewById(R.id.iv);
        }
    }
}
