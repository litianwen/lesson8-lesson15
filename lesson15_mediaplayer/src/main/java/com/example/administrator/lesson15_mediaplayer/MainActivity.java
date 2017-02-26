package com.example.administrator.lesson15_mediaplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    MediaPlayer mediaPlayer;
    Button btn_play, btn_stop;
    SeekBar sb;
    TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_play = (Button) findViewById(R.id.btn_play);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_play.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_stop.setEnabled(false);
        sb = (SeekBar) findViewById(R.id.sb);
        sb.setOnSeekBarChangeListener(this);
        time = (TextView) findViewById(R.id.time);
        //已经做了预加载
        //同步预加载
//        mediaPlayer.start();
//        mediaPlayer.pause();
//        mediaPlayer.stop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stop();
    }

    public void stop() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying())
                mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            btn_stop.setEnabled(false);
            btn_play.setText("播放");
            sb.setProgress(0);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.beyond_bzyy);
                    //加载完毕后，知道音乐的时常
                    Log.e("TAG", mediaPlayer.getDuration() + "    ----");
                    sb.setMax(mediaPlayer.getDuration() / 1000);
                    mediaPlayer.start();
                    startTime();
                    btn_play.setText("暂停");
                    btn_stop.setEnabled(true);
                } else {
                    //播放与暂停
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        timer.cancel();
                        btn_play.setText("继续");
                    } else {
                        mediaPlayer.start();
                        startTime();
                        btn_play.setText("暂停");
                    }
                }
                break;
            case R.id.btn_stop:
                stop();
                timer.cancel();
                break;
        }
    }

    Timer timer;
    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

    public void startTime() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        sb.setProgress(sb.getProgress() + 1);
                        long len = sb.getProgress() * 1000;
                        time.setText(format.format(new Date(len)) + "/" + format.format(new Date(mediaPlayer.getDuration())));

                    }
                });
            }
        }, 1000, 1000);

    }


    boolean fromUser;

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        this.fromUser = fromUser;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        fromUser = false;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (fromUser) {
            int time = seekBar.getProgress() * 1000;
            mediaPlayer.seekTo(time);
        }
    }
}
