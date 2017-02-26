package com.example.administrator.lesson9_activitylaunchmode;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void standard(View v) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void singleTop(View v) {
        startActivity(new Intent(this, SingleTopActivity.class));
    }

    public void singleInstance(View v) {
        Intent intent = new Intent();
        //所有的应用程序跳转 都是通过Comonent来完成的
        ComponentName pn = new ComponentName("com.example.administrator.lesson9_signleinstance", "com.example.administrator.lesson9_signleinstance.MainActivity");
        intent.setComponent(pn);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String msg = data.getStringExtra("msg");
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
