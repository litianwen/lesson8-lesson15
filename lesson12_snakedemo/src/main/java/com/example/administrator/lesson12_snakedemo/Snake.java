package com.example.administrator.lesson12_snakedemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */

public class Snake extends Thread {

    //10个等级
    int guanqia = 9;

    public static final int LEFT = 11;
    public static final int RIGHT = 12;
    public static final int UP = 23;
    public static final int DOWN = 24;

    public int direction = UP;


    @Override
    public void run() {
        super.run();
        while (!isOver()) {
            try {
                Thread.sleep(1050 - guanqia * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            move();

        }
    }

    public boolean isOver() {
        Point point = mList.get(0);
        if (point.x < 0 || point.x == x || point.y < 0 || point.y == y)
            return true;
        //撞到自己
        for (int i = 1; i < mList.size(); i++) {
            if (point.x == mList.get(i).x && point.y == mList.get(i).y)
                return true;
        }
        return false;
    }

    private final int y;
    private final int x;
    private int width;

    Paint paint = new Paint();

    //知道每个蛇的大小
    public Snake(int width, int x, int y) {
        this.width = width;
        this.x = x;
        this.y = y;
        paint.setColor(Color.GREEN);

        //3个点
        mList.add(new Point(x / 2, y / 2));
        mList.add(new Point(x / 2, y / 2 + 1));
        mList.add(new Point(x / 2, y / 2 + 2));
    }

    //蛇

    List<Point> mList = new ArrayList<>();


    //能绘制自己
    public void myDraw(Canvas canvas) {
        for (Point point : mList) {
            canvas.drawRect(point.x * width, point.y * width, (point.x + 1) * width, (point.y + 1) * width, paint);
        }
    }


    //移动
    public void move() {
        if (tempDriection / 10 != direction / 10)
            direction = tempDriection;
        Point p = mList.get(mList.size() - 1);
        Point point = new Point(p.x, p.y);
        switch (direction) {
            case LEFT:
                point.x = mList.get(0).x - 1;
                point.y = mList.get(0).y;
                break;
            case RIGHT:
                point.x = mList.get(0).x + 1;
                point.y = mList.get(0).y;
                break;
            case UP:
                point.x = mList.get(0).x;
                point.y = mList.get(0).y - 1;
                break;
            case DOWN:
                point.x = mList.get(0).x;
                point.y = mList.get(0).y + 1;
                break;
        }

        if (food == null)
            mList.remove(mList.get(mList.size() - 1));
        else
            food = null;
        mList.add(0, point);
    }

    //缓冲
    public void setDriection(int driection) {
        this.tempDriection = driection;
    }

    int tempDriection = direction;


    //判断是否在蛇身上
    public boolean isConnPoint(Point point) {
        for (Point point1 : mList) {
            if (point.equals(point1.x, point1.y))
                return true;
        }
        return false;
    }

    public boolean eat(Point point) {
        Point head = mList.get(0);
        if (head.equals(point.x, point.y)) {
            food = point;
            return true;
        }
        return false;
    }

    Point food = null;

}
