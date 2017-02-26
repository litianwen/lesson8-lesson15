package com.example.administrator.lesson12_diyprogressbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArcProgressBar apb = (ArcProgressBar) findViewById(R.id.apb);
        apb.setOnProgressCompleteListener(new ArcProgressBar.OnProgressCompleteListener() {
            @Override
            public void onFinish() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "执行完毕", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        apb.start();
    }
}
