package com.example.administrator.lesson13_sharedpre;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    EditText et_username, et_password;
    TextView tv_login;
    CheckBox cb_remebr, cb_auto;

    SPHelper spHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spHelper = new SPHelper(this, "login");
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        tv_login = (TextView) findViewById(R.id.tv_login);
        cb_remebr = (CheckBox) findViewById(R.id.cb_remem);
        cb_auto = (CheckBox) findViewById(R.id.cb_auto);
        cb_auto.setOnCheckedChangeListener(this);
        cb_remebr.setOnCheckedChangeListener(this);
        tv_login.setOnClickListener(this);
        //用户打开界面，我需要读取所有的信息
        SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
//        spHelper.getBoolean("isRemebr");
        boolean b = sp.getBoolean("isRemebr", false);
        if (b) {
            cb_remebr.setChecked(true);
            String username = sp.getString("username", "");
            String password = sp.getString("password    ", "");
            et_username.setText(username);
            et_password.setText(password);
            cb_auto.setChecked(sp.getBoolean("isAuto", false));
        }

//        //xml文件
//        SharedPreferences sp = getSharedPreferences("Setting", MODE_PRIVATE);
//        //存储一些数据
//        boolean b = sp.getBoolean("isFirst", true);
//        Log.e("TAG", "------------------" + b);
//        if (b) {
//            //启动引导页
//            //启动后 ， 写入false
//            //开始编辑
//            Toast.makeText(this, "第一次登陆", Toast.LENGTH_SHORT).show();
//            SharedPreferences.Editor et = sp.edit();
//            et.putBoolean("isFirst", false);
//            et.commit();
//        } else {
//            Toast.makeText(this, "不是第一次登陆", Toast.LENGTH_SHORT).show();
//        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //勾选状态
        if (buttonView == cb_remebr) {
            if (!isChecked) {
                cb_auto.setChecked(false);
            }
        } else {
            if (isChecked) {
                cb_remebr.setChecked(true);
            }
        }

    }


    String[] user = {"zhangsan", "lisi", "wangwu", "tianqi", "zhaoliu"};

    @Override
    public void onClick(View v) {
        String password = et_password.getText().toString();
        String username = et_username.getText().toString();
        for (String s : user) {
            if (username.equals(s) && password.equals("123456")) {
                if (cb_remebr.isChecked()) {
                    //保存用户的资料
                    SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor et = sp.edit();
                    et.putBoolean("isRemebr", true);
                    et.putString("username", username);
                    et.putString("password", password);
                    et.putBoolean("isAuto", cb_auto.isChecked());
                    et.commit();

//                    spHelper.putValue(new SPHelper.ContentValue("",""),new SPHelper.ContentValue(),new SPHelper.ContentValue());

                } else {
                    //清空账户
                    SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
                    sp.edit().clear().commit();
                }
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
    }
}
