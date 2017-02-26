package com.example.administrator.ninegongdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NineView view = new NineView(this);
        setContentView(view);
        view.setOnPasswordFinishListener(new NineView.OnPasswordFinishListener() {
            @Override
            public void onPasswordFinish(String password) {
                Toast.makeText(getBaseContext(), "密码:" + password, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
