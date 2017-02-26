package com.example.administrator.lesson9_activity1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //重新运行整个生命周期

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //alt+enter
        et = (EditText) findViewById(R.id.et);
        Log.e("TAG", "-->>>onCreate");
        if (savedInstanceState != null) {
            msg = savedInstanceState.getString("msg");
        }
    }


    String msg = "";

    public void save(View v) {
        msg = et.getText().toString();
    }

    public void toast(View v) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e("TAG", "-->>>onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("TAG", "-->>>onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "-->>>onDestroy");
    }

    //在Activity被系统回收时 调用
    //会在任何系统有可能回收该activity时调用
    //保存状态
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //onPause只后调用
        super.onSaveInstanceState(outState);
        Log.e("TAG", "-->>>onSaveInstanceState");
        outState.putString("msg", msg);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("TAG", "-->>>onRestoreInstanceState");
        //
    }
}
