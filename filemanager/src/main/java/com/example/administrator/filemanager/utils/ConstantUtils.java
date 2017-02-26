package com.example.administrator.filemanager.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;

import com.example.administrator.filemanager.R;

import java.io.File;

/**
 * Created by Administrator on 2016/11/4.
 */

public class ConstantUtils {

    public static final String[] gridTitle =
            {
                    "日志"
                    , "快传"
                    , "压缩文件"
                    , "清理"
                    , "应用"
                    , "图片"
                    , "音乐"
                    , "视频"
                    , "网盘"
                    , "下载"
                    , "文档"
                    , "我的网络"
                    , "回收站"
                    , "远程管理"
                    , "应用锁"
                    , "加密库"
            };

    public static final int[] resId =
            {
                    R.mipmap.library_logger,
                    R.mipmap.library_sender,
                    R.mipmap.library_compress,
                    R.mipmap.library_clean,
                    R.mipmap.library_app,
                    R.mipmap.library_image,
                    R.mipmap.library_musicplay,
                    R.mipmap.library_video,
                    R.mipmap.library_cloud,
                    R.mipmap.library_download,
                    R.mipmap.library_document,
                    R.mipmap.library_device,
                    R.mipmap.library_recyclebin,
                    R.mipmap.library_viewonpc,
                    R.mipmap.library_applocker,
                    R.mipmap.library_encryption,
            };

    //按照文件后缀去返回不同的图片格式
    public static final Bitmap getPicByFile(Context conetxt, File file) {
        if (file.isDirectory()) {
            return BitmapFactory.decodeResource(conetxt.getResources(), R.mipmap.format_folder);
        } else {
            //aalksdfjlajdf.asdfa.asdf.asdf
            if (file.getName().lastIndexOf(".") != -1) {
                return BitmapFactory.decodeResource(conetxt.getResources(), R.mipmap.format_unkown);
            }
            String endwith = file.getName().substring(file.getName().lastIndexOf("."));
            switch (endwith) {
                case ".png":
                case ".jpg":
                    return ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(file.getAbsolutePath()), 100, 100);
                case ".xls":
                    return BitmapFactory.decodeResource(conetxt.getResources(), R.mipmap.format_excel);
                case ".bt":
                    return BitmapFactory.decodeResource(conetxt.getResources(), R.mipmap.format_torrent);
                case ".zip":
                case ".jar":
                case ".rar":
                    return BitmapFactory.decodeResource(conetxt.getResources(), R.mipmap.format_zip);
                case ".apk":
                    return BitmapFactory.decodeResource(conetxt.getResources(), R.mipmap.format_apk);
                case ".chm":
                    return BitmapFactory.decodeResource(conetxt.getResources(), R.mipmap.format_chm);
                case ".txt":
                case ".log":
                case ".ini":
                    return BitmapFactory.decodeResource(conetxt.getResources(), R.mipmap.format_text);
                case ".flash":
                    return BitmapFactory.decodeResource(conetxt.getResources(), R.mipmap.format_flash);
                case ".mp4":
                case ".3gp":
                    return BitmapFactory.decodeResource(conetxt.getResources(), R.mipmap.format_media);
                case ".mp3":
                case ".wav":
                    return BitmapFactory.decodeResource(conetxt.getResources(), R.mipmap.format_music);
                default:
                    return BitmapFactory.decodeResource(conetxt.getResources(), R.mipmap.format_unkown);
            }
        }
    }


}
