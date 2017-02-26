package com.example.administrator.lesson9_activitylaunchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2016/10/18.
 */

public class SingleTopActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        Log.e("TAG", "---->>>onCreate");
    }

    public void singleTop(View v) {
        startActivity(new Intent(this, SingleTopActivity.class));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("TAG", "------------>>");
    }

    public void jump(View v) {
        startActivity(new Intent(this, ThreeActivity.class));
    }
}
