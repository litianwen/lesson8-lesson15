package com.example.administrator.filemanager.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.filemanager.R;
import com.example.administrator.mylib.adapter.ListItemAdapter;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2016/11/4.
 */

public class OtherAdapter extends ListItemAdapter<File> {
    int type;

    public OtherAdapter(Context context, List<File> list, int type) {
        super(context, list);
        this.type = type;
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
        switch (type) {
            case 1://加载缩略图
                //
                Bitmap bmp = BitmapFactory.decodeFile(getItem(position).getAbsolutePath());
                Bitmap b = ThumbnailUtils.extractThumbnail(bmp, 100, 100);
                holder.iv.setImageBitmap(b);
                break;
            case 2:
                holder.iv.setImageResource(R.mipmap.format_music);
                break;
            case 3:
                Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(getItem(position).getAbsolutePath(), MediaStore.Images.Thumbnails.MINI_KIND);
                holder.iv.setImageBitmap(bitmap);
                break;
        }
        holder.tv.setText(getItem(position).getName());
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
