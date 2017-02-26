package com.example.administrator.filemanager.bean;

/**
 * Created by Administrator on 2016/11/4.
 */

public class HistoryBean {

    public static final int TYPE_MUSIC = 2;
    public static final int TYPE_PIC = 1;
    public static final int TYPE_VIDEO = 3;

    public long id;

    public String filePath;

    public long lastTime;

    public int type;

}
