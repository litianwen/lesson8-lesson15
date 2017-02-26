package com.example.administrator.lesson10_fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("TAG","--------activity---onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("TAG","--------activity---onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("TAG","--------activity---onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("TAG","--------activity---onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("TAG","--------activity---onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("TAG","--------activity---onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("TAG","--------activity---onRestart");
    }
}
