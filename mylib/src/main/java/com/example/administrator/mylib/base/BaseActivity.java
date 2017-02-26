package com.example.administrator.mylib.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/4.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void jumpActivity(Class cls) {
        startActivity(new Intent(this, cls));
    }

}
