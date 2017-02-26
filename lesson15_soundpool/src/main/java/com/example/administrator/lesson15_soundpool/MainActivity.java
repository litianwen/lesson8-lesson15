package com.example.administrator.lesson15_soundpool;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;

    SoundPool soundPool;

    HashMap<Integer, Integer> map = new HashMap<>();
//    SparseIntArray  == HashMap<Integer, Integer>
//    SparseBooleanArray == HashMap<Integer,Boolean>


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //构造
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        //加载资源 异步
        map.put(1, soundPool.load(this, R.raw.chimes, 100));
        map.put(2, soundPool.load(this, R.raw.ding, 100));
        map.put(3, soundPool.load(this, R.raw.enter, 100));
        map.put(4, soundPool.load(this, R.raw.notify, 100));
        map.put(5, soundPool.load(this, R.raw.ringout, 100));
        //不能在这里播放
//        int id = soundPool.load(this, R.raw.chimes, 100);
//        soundPool.load(this, R.raw.ding, 100);
//        soundPool.load(this, R.raw.enter, 100);
//        soundPool.load(this, R.raw.notify, 100);
//        soundPool.load(this, R.raw.ringout, 100);
    }

    public void play(View v) {
        mp = new MediaPlayer();
        //设置
        try {
            mp.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath() + "/themass.mp3");
            //预加载
            //同步方法，如果歌曲小
            long start = System.currentTimeMillis();
            mp.prepare();
            System.out.println("---------------" + (System.currentTimeMillis() - start));
            mp.start();
            //异步需要设置监听
//            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    mp.start();
//                }
//            });
//            //异步加载方法
//            mp.prepareAsync();
//            mp.setOnSeekCompleteListener();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play1(View v) {
        soundPool.play(map.get(1), 1, 1, 0, 0, 1);
    }

    public void play2(View v) {
        soundPool.play(map.get(2), 1, 1, 0, 0, 1);
    }

    public void play3(View v) {
        soundPool.play(map.get(3), 1, 1, 0, 0, 1);
    }

    public void play4(View v) {
        soundPool.play(map.get(4), 1, 1, 0, 0, 1);
    }

    public void play5(View v) {
        soundPool.play(map.get(5), 1, 1, 0, 0, 1);
    }

}