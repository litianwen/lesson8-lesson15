package com.example.administrator.lesson12_diyprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/10/25.
 */

public class ArcProgressBar extends View {

    Paint paint;
    Paint textPaint;

    private int max = 100;

    private int progress = 0;


    int width;
    private RectF oval;

    public ArcProgressBar(Context context) {
        super(context);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量布局之后 才能获取宽度
        width = getWidth();
        oval = new RectF(width / 2 - 100, width / 2 - 100, width / 2 + 100, width / 2 + 100);
    }

    public ArcProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取自定义属性
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ArcProgressBar);
        //取出我们的属性
        max = a.getInt(R.styleable.ArcProgressBar_max, 100);
        progress = a.getInt(R.styleable.ArcProgressBar_progress, 0);
        //一定要释放
        a.recycle();
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.BLUE);
        textPaint.setTextSize(30);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //灰色圆在背后
        paint.setColor(Color.GRAY);
        canvas.drawCircle(width / 2, width / 2, 100, paint);
        //绘制一个弧线 进度
        paint.setColor(Color.RED);
        //结束角度 进度/max   50/100; 0.5 *360
        canvas.drawArc(oval, -90, (int) (progress * 1f / max * 360), false, paint);
        //绘制百分比文本
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText((int) (progress * 1f / max * 100f) + "%", width / 2, width / 2, textPaint);
    }

    //模拟启动
    public void start() {
        new Thread() {
            @Override
            public void run() {
                while (progress < max) {
                    progress++;
                    try {
                        Thread.sleep(80);
                        postInvalidate();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (onProgressCompleteListener != null) {
                    onProgressCompleteListener.onFinish();
                }
            }
        }.start();
        //主线程刷新
        // invalidate();
    }


    //1.创建监听
    public interface OnProgressCompleteListener {
        void onFinish();
    }

    //2.创建接口对象
    OnProgressCompleteListener onProgressCompleteListener;

    //3. set方法
    public void setOnProgressCompleteListener(OnProgressCompleteListener onProgressCompleteListener) {
        this.onProgressCompleteListener = onProgressCompleteListener;
    }
}
