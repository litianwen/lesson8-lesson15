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
import com.example.administrator.filemanager.bean.HistoryBean;
import com.example.administrator.filemanager.utils.FileSizeUtils;
import com.example.administrator.mylib.adapter.ListItemAdapter;
import com.example.administrator.mylib.utils.TimeUtils;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/11/4.
 */

public class OtherHistoryAdapter extends ListItemAdapter<HistoryBean> {

    public OtherHistoryAdapter(Context context, List<HistoryBean> list) {
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
        holder.tvExtratto.setVisibility(View.INVISIBLE);
        HistoryBean bean = getItem(position);
        switch (bean.type) {
            case 1://加载缩略图
                //
                Bitmap bmp = BitmapFactory.decodeFile(getItem(position).filePath);
                Bitmap b = ThumbnailUtils.extractThumbnail(bmp, 100, 100);
                holder.ivFiletype.setImageBitmap(b);
                break;
            case 2:
                holder.ivFiletype.setImageResource(R.mipmap.format_music);
                break;
            case 3:
                Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(getItem(position).filePath, MediaStore.Images.Thumbnails.MINI_KIND);
                holder.ivFiletype.setImageBitmap(bitmap);
                break;
        }
        File file = new File(bean.filePath);
        holder.tvFilename.setText(file.getName());
        holder.tvFiletime.setText(TimeUtils.getTime(bean.lastTime, "yyyy-MM-dd"));
        holder.tvFilesize.setText(FileSizeUtils.getSizeFromKM(file.length()));
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_filetype)
        ImageView ivFiletype;
        @BindView(R.id.tv_filename)
        TextView tvFilename;
        @BindView(R.id.tv_filesize)
        TextView tvFilesize;
        @BindView(R.id.tv_extratto)
        TextView tvExtratto;
        @BindView(R.id.tv_filetime)
        TextView tvFiletime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

