package com.example.administrator.filemanager.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Administrator on 2016/11/4.
 */

public class MySqliteOpenHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "filemanager.db";


    public MySqliteOpenHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table history(id integer primary key autoincrement,filepath text not null,lasttime long not null,type integer not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
