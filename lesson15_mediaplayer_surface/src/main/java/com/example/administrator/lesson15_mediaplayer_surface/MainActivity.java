package com.example.administrator.lesson15_mediaplayer_surface;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.MediaController;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    SurfaceView sv;
    MediaPlayer mp;
    MediaController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sv = (SurfaceView) findViewById(R.id.sv);
        mc = (MediaController) findViewById(R.id.mc);
        SurfaceHolder holder = sv.getHolder();
        //设置监听
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //播放视频
        mp = MediaPlayer.create(this, R.raw.hd);
        mp.setDisplay(holder);
        mp.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mp != null)
            mp.release();
        mp = null;
    }

    public void pause(View v) {
        if (mp.isPlaying())
            mp.pause();
        else
            mp.start();
    }

}
