package com.example.administrator.lesson15_videoview;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vv = (VideoView) findViewById(R.id.vv);
        vv.setVideoPath(Environment.getExternalStorageDirectory().getAbsolutePath() + "/hd.mp4");
        //创建一个控制器
        MediaController mc = new MediaController(this);
        vv.setMediaController(mc);
        vv.start();
    }
}
