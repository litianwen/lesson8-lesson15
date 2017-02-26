package com.example.administrator.ninegongdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/28.
 */

public class NineView extends View {

    int width;
    Paint paintback = new Paint();
    Paint paintsrc = new Paint();
    int background;

    //保证是正方形

    int max = 6; //密码的个数   6
    int min = 4;


    //点在哪里
    float currX, currY;

    public NineView(Context context) {
        super(context);
        init();
    }

    public NineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        paintback.setDither(true);
        paintback.setAntiAlias(true);
        paintsrc.setDither(true);
        paintsrc.setAntiAlias(true);
        //171625
        background = Color.rgb(0x17, 0x16, 0x25);
        paintback.setColor(background);
        //3791E6
        paintsrc.setColor(Color.rgb(0x37, 0x91, 0xe6));


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getWidth() / 4;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //清屏
        canvas.drawColor(background);
        //划线
        if (result.size() > 0) {


            //点
            int x = result.get(result.size() - 1) % 3 + 1;
            int y = result.get(result.size() - 1) / 3 + 1;
            paintsrc.setStrokeWidth(10);
            canvas.drawLine(x * width, y * width, currX, currY, paintsrc);
            canvas.drawCircle(x * width, y * width, width / 3, paintback);
            if (result.size() > 1) {
                //防止越界
                for (int i = 0; i < result.size() - 1; i++) { // 1 2 3  <=2
                    //需要取当前的i和下一个i
                    //按住的前一个点
                    int x1 = result.get(i) % 3 + 1;
                    int y1 = result.get(i) / 3 + 1;
                    //按住的后一个点
                    int x2 = result.get(i + 1) % 3 + 1;
                    int y2 = result.get(i + 1) / 3 + 1;
                    paintsrc.setStrokeWidth(10);
                    canvas.drawLine(x1 * width, y1 * width, x2 * width, y2 * width, paintsrc);
                    canvas.drawCircle(x1 * width, y1 * width, width / 3, paintback);

                }
            }
        }
        paintsrc.setStrokeWidth(2);
        //9个圆
        paintsrc.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                canvas.drawCircle((i + 1) * width, (j + 1) * width, width / 3, paintsrc);
            }
        }
        paintsrc.setStyle(Paint.Style.FILL);
        for (Integer integer : result) {
            //i j ;  // 8   2  2
            int j = integer / 3 + 1;
            int i = integer % 3 + 1;
            canvas.drawCircle(i * width, j * width, width / 8, paintsrc);
        }

    }

    //密码
    List<Integer> result = new ArrayList<>();

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //勾股定理
                int i = isConnPoint(x, y);
                //只要在园内
                if (i != -1) {
                    result.add(i);
                    currX = x;
                    currY = y;
                }
                Log.e("TAG", "=====" + i);
                break;
            case MotionEvent.ACTION_MOVE:
                currX = x;
                currY = y;
                //移动到其他的圆中，那么接着去添加result
                int point = isConnPoint(x, y);
                if (point != -1 && !result.contains((Integer) point)) {
                    result.add(point);
                    if (result.size() > max) {
                        //reslut清空
                        if (onPasswordFinishListener != null)
                            onPasswordFinishListener.onPasswordFinish(getPassword());
                        result.clear();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (result.size() >= min) {
                    if (onPasswordFinishListener != null)
                        onPasswordFinishListener.onPasswordFinish(getPassword());
                }
                result.clear();
                break;
        }
        invalidate();
        return true;
    }

    public String getPassword() {
        String password = "";
        for (Integer integer : result) {
            password += integer + "";
        }
        return password;
    }


    //判断
    public int isConnPoint(float x, float y) {
        //9    width,width  width
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (pointOnCircle(x, y, (j + 1) * width, (i + 1) * width)) {
                    return i * 3 + j; //0-8
                }
            }
        }
        return -1;
    }

    public boolean pointOnCircle(float x, float y, int cx, int cy) {//true
        Log.e("TAG", ((cx - x) * (cx - x) + (cy - y) * (cy - y)) + "");
        Log.e("TAG", ((float) width / 3f) * ((float) width / 3f) + "");
        float i = ((cx - x) * (cx - x) + (cy - y) * (cy - y));
        float j = ((float) width / 3f) * ((float) width / 3f);
        return i < j;
    }


    public void setOnPasswordFinishListener(OnPasswordFinishListener onPasswordFinishListener) {
        this.onPasswordFinishListener = onPasswordFinishListener;
    }

    private OnPasswordFinishListener onPasswordFinishListener;

    public interface OnPasswordFinishListener {
        void onPasswordFinish(String password);
    }

}
