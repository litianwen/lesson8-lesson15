package com.example.administrator.lesson13_sqlitedatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 2016/11/1.
 */

public class MySqliteOpenHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Test.db";

    public static final String TABLE_NAME = "userInfo";

    public static final String ID = "_id";

    public static final String USER_USERNAME = "username";

    public static final String USER_AGE = "age";

    public static final String USER_SEX = "sex";

    public static final String USER_INFO = "info";

    public static final String CREATE_TABLE = "create table " + TABLE_NAME + " (" + ID + " integer primary key autoincrement," + USER_USERNAME + " text not null," + USER_AGE + " integer," + USER_SEX + " varchar(1)," + USER_INFO + " text" + ")";


    public static final String DELETE_TABLE = "drop table if exists " + TABLE_NAME;
    //用户资料 id 用户名 性别 年龄  info

    //数据库的名称
    public MySqliteOpenHelper(Context context) {
        //上下文，数据库名称，游标工厂,
        super(context, DB_NAME, null, 2);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建时
        //没有数据库的时候，回调该方法
        //建表
        Log.e("TAG", CREATE_TABLE);
        db.execSQL(CREATE_TABLE);
        Log.e("TAG", "-------------执行onCreate");
        db.execSQL("create table aa(id integer primary key,aaa text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //修改列
        //取数据
        //删除表
        //新建表
        //插入数据
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }
}
