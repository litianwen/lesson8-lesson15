package com.example.administrator.lesson12_snakedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Administrator on 2016/10/31.
 */

public class SnakeSurfaceView extends SurfaceView implements Runnable, SurfaceHolder.Callback {

    Paint paint = new Paint();
    Snake snake;
    //食物
    Point food;

    int score = 0;


    int x, y;

    public SnakeSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    public SnakeSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }


    //19 15

    public void myDraw() {
        Canvas canvas = getHolder().lockCanvas();
        //绘制背景
        canvas.drawColor(Color.WHITE);
        //绘制线条
        paint.setColor(Color.GRAY);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 15; j++) {
                //横线
                canvas.drawLine(0, i * x, getWidth(), i * x, paint);
                canvas.drawLine(j * x, 0, j * x, x * 19, paint);
            }
        }
        //绘制食物
        paint.setColor(Color.RED);
        canvas.drawRect(food.x * x + 5, food.y * x + 5, (food.x + 1) * x - 5, (food.y + 1) * x - 5, paint);
        snake.myDraw(canvas);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(24);
        canvas.drawText("分数:" + score, 0, getHeight() - 50, paint);
        getHolder().unlockCanvasAndPost(canvas);
    }


    @Override
    public void run() {
        //不断循环  贴图
        //需要动
        while (!snake.isOver()) {
            myDraw();
            if (snake.eat(food)) {
                createFood();
                //给一个分数
                score += 100;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //结束
        over();
    }

    private void over() {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(48);
        Canvas canvas = getHolder().lockCanvas();
        canvas.drawText("游戏结束", getWidth() / 2, getHeight() / 2, paint);
        getHolder().unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.GRAY);
        x = getWidth() / 15;
        y = getHeight() / 20;
        snake = new Snake(x, 15, 19);
        //创建第一个食物
        createFood();
        //自己开始跑
        new Thread(this).start();
        snake.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }


    float oldx;
    float oldy;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //手势
        //左滑，右滑，
        //上下
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldx = x;
                oldy = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //判断 到底是上下还是左右
                if (Math.abs(oldx - x) >= Math.abs(oldy - y)) {// zuoyou
                    //25个像素
                    if (oldx - x > 10) //←
                    {
                        snake.setDriection(Snake.LEFT);
                    } else if (oldx - x < -10) {
                        snake.setDriection(Snake.RIGHT);
                    }
                } else {
                    //上下
                    if (oldy - y > 10) {
                        snake.setDriection(Snake.UP);

                    } else if (oldy - y < -10) {
                        snake.setDriection(Snake.DOWN);
                    }
                }
                oldx = x;
                oldy = y;
                break;
            case MotionEvent.ACTION_UP:
                if (snake.isOver()) {
                    //重新开始
//                    new Thread(this).start();
                }
                break;
        }
        return true;
    }


    //初始化食物
    public void createFood() {
        //食物不能刷在蛇身上
        Point point = new Point();
        do {
            point.x = (int) (Math.random() * 15);
            point.y = (int) (Math.random() * 19);
        } while (snake.isConnPoint(point));
        food = point;
    }
}
