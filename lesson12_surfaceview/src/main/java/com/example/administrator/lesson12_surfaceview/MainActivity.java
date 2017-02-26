package com.example.administrator.lesson12_surfaceview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    SurfaceView sv;
    SurfaceHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //使用控件
        sv = (SurfaceView) findViewById(R.id.sv);
        //获取控制器
        holder = sv.getHolder();
        //添加一个监听
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(final SurfaceHolder holder) {
        //创建
        //只有在生命周期中，才能获取surfaceView的高度和宽度
        new Thread() {
            Paint paint = new Paint();

            @Override
            public void run() {
                paint.setColor(Color.RED);
                paint.setAntiAlias(true);
                paint.setDither(true);
                super.run();
                int index = 0;
                while (index++ < 100) {
                    int x = sv.getWidth() / 100 * index;
                    int y = sv.getHeight() / 100 * index;
                    //绘制
                    //锁住屏幕的一块矩阵，然后开始绘制
                    Canvas canvas = holder.lockCanvas();
                    //清屏
                    canvas.drawColor(Color.WHITE);
                    canvas.drawCircle(x, y, 20, paint);
                    //解锁并且复制到主界面
                    holder.unlockCanvasAndPost(canvas);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //大小修改， 横竖屏切换时
        //至少执行一次

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //销毁
    }


}
