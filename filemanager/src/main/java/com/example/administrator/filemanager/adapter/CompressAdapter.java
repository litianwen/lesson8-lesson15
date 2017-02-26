package com.example.administrator.filemanager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.filemanager.R;
import com.example.administrator.filemanager.utils.FileSizeUtils;
import com.example.administrator.mylib.adapter.ListItemAdapter;
import com.example.administrator.mylib.utils.TimeUtils;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2016/11/4.
 */

public class CompressAdapter extends ListItemAdapter<File> {

    public CompressAdapter(Context context, List<File> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.compress_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        holder.bindView(getItem(position));
        return convertView;
    }

    class ViewHolder {
        ImageView iv_filetype;
        TextView tv_filename, tv_filetime, tv_filesize;

        public ViewHolder(View convertView) {
            iv_filetype = (ImageView) convertView.findViewById(R.id.iv_filetype);
            tv_filename = (TextView) convertView.findViewById(R.id.tv_filename);
            tv_filetime = (TextView) convertView.findViewById(R.id.tv_filetime);
            tv_filesize = (TextView) convertView.findViewById(R.id.tv_filesize);
        }

        public void bindView(File file) {
            tv_filename.setText(file.getName());
            tv_filesize.setText(FileSizeUtils.getSizeFromKM(file.length()));
            tv_filetime.setText(TimeUtils.getTime(file.lastModified(), "yyyy.MM.dd"));
        }
    }
}
