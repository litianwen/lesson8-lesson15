package com.example.administrator.lesson13_sdcared;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText et_content;

    /**
     * 在没有外部存储器的情况下 使用他
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_content = (EditText) findViewById(R.id.et_contnet);

    }

    public void save(View v) {
        try {
            FileOutputStream fos = openFileOutput("file", MODE_APPEND);
            fos.write(et_content.getText().toString().getBytes());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void read(View v) {
        FileInputStream fis = null;
        try {
            fis = openFileInput("file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] buf = new byte[1024];
        int len;
        try {
            while ((len = fis.read(buf)) != -1) {
                et_content.append(new String(buf, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void check(View v) {
        //获取存储器状态
        String state = Environment.getExternalStorageState();
        //sd卡就绪状态
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            //外部存储器
            Toast.makeText(this, "可以使用外部存储器", Toast.LENGTH_SHORT).show();
        } else {
            //只能使用内部存储器
            Toast.makeText(this, "只能使用内部存储器", Toast.LENGTH_SHORT).show();
        }
    }
}
