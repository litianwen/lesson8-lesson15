package com.example.administrator.filemanager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.filemanager.bean.HistoryBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/4.
 */

public class HistoryDAO {

    MySqliteOpenHelper helper;

    public HistoryDAO(Context context) {
        helper = new MySqliteOpenHelper(context);
    }

    //插入一条记录
    public void insert(HistoryBean bean) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("filepath", bean.filePath);
        values.put("lasttime", bean.lastTime);
        values.put("type", bean.type);
        db.insert("history", null, values);
        db.close();
    }

    //读取
    public List<HistoryBean> selectByType(int type) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("history", null, "type=?", new String[]{type + ""}, null, null, null);
        if (cursor == null)
            return null;
        List<HistoryBean> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            HistoryBean bean = new HistoryBean();
            bean.filePath = cursor.getString(cursor.getColumnIndex("filepath"));
            bean.id = cursor.getLong(cursor.getColumnIndex("id"));
            bean.lastTime = cursor.getLong(cursor.getColumnIndex("lasttime"));
            bean.type = type;
            list.add(bean);
        }
        db.close();
        return list;
    }

}
