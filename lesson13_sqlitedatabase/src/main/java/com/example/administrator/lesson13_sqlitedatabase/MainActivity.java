package com.example.administrator.lesson13_sqlitedatabase;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    SQLiteDatabase db;
    ListView lv;
    List<User> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        MySqliteOpenHelper helper = new MySqliteOpenHelper(this);
        //可读，并且可写   磁盘满的情况，写不进去，不写了
        db = helper.getReadableDatabase();
        lv.setAdapter(adapter);
//        //可读，并且可写   磁盘满的情况，写不进去。报错，崩溃了
//        helper.getWritableDatabase();
        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);
    }

    public void insert(View v) {
        //1 插入
        ContentValues values = new ContentValues();
        values.put(MySqliteOpenHelper.USER_USERNAME, "张三");
        values.put(MySqliteOpenHelper.USER_AGE, 58);
        values.put(MySqliteOpenHelper.USER_SEX, "男");
        values.put(MySqliteOpenHelper.USER_INFO, "IT工程师---张工");
        db.insert(MySqliteOpenHelper.TABLE_NAME, null, values);
        select();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    public void select(View v) {
        select();
    }

    private void select() {
        //显示
        mList.clear();
//        db.query(MySqliteOpenHelper.TABLE_NAME, null, MySqliteOpenHelper.ID + "=?", new String[]{1 + ""}, null, null, null, null);
        Cursor cursor = db.query(MySqliteOpenHelper.TABLE_NAME, null, null, null, null, null, null, null);
        //结果集 ,默认指向表头
        while (cursor.moveToNext()) {
            User user = new User();
            //cursor.getColumnIndex()获取列名所在的列号
            user.id = cursor.getInt(cursor.getColumnIndex(MySqliteOpenHelper.ID));
            user.age = cursor.getInt(cursor.getColumnIndex(MySqliteOpenHelper.USER_AGE));
            user.username = cursor.getString(cursor.getColumnIndex(MySqliteOpenHelper.USER_USERNAME));
            user.info = cursor.getString(cursor.getColumnIndex(MySqliteOpenHelper.USER_INFO));
            user.sex = cursor.getString(cursor.getColumnIndex(MySqliteOpenHelper.USER_SEX));
            mList.add(user);
        }
        //刷新适配器
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //修改
        User user = mList.get(position);
        user.sex = "女";
        user.age = 25;
        user.info = "java工程师";

        //修改
        ContentValues values = new ContentValues();
        values.put(MySqliteOpenHelper.USER_INFO, user.info);
        values.put(MySqliteOpenHelper.USER_AGE, user.age);
        values.put(MySqliteOpenHelper.USER_SEX, user.sex);
        db.update(MySqliteOpenHelper.TABLE_NAME, values, MySqliteOpenHelper.ID + "=?", new String[]{user.id + ""});
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        //删除
        new AlertDialog.Builder(this)
                .setTitle("警告")
                .setMessage("是否删除该数据")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        User user = mList.get(position);
                        db.delete(MySqliteOpenHelper.TABLE_NAME, MySqliteOpenHelper.ID + "=?", new String[]{user.id + ""});
                        mList.remove(user);
                        adapter.notifyDataSetChanged();
                    }
                }).show();
        return true;
    }

    class User {
        int id;
        String username;
        String info;
        int age;
        String sex;
    }

    private BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(getBaseContext(), R.layout.item_layout, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);

            } else
                holder = (ViewHolder) convertView.getTag();
            User user = mList.get(position);
            holder.age.setText(user.age + ""); //ResourceNotFound
            holder.info.setText(user.info + "");
            holder.username.setText(user.username + "");
            holder.sex.setText(user.sex + "");
            holder.id.setText(user.id + "");
            return convertView;
        }

        class ViewHolder {
            TextView id, info, sex, username, age;

            public ViewHolder(View convertView) {
                id = (TextView) convertView.findViewById(R.id.id);
                info = (TextView) convertView.findViewById(R.id.info);
                sex = (TextView) convertView.findViewById(R.id.sex);
                username = (TextView) convertView.findViewById(R.id.username);
                age = (TextView) convertView.findViewById(R.id.age);
            }


        }
    };
}
