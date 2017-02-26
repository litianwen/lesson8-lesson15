package com.example.administrator.lesson12_diyview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by Administrator on 2016/10/25.
 */

public class MyView extends View {

    //画笔
    Paint paint = new Paint();

    Point point = new Point();


    //保证xml中能初始化
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        //抗锯齿
        paint.setAntiAlias(true);
        //防抖动
        paint.setDither(true);
        //设置颜色
        paint.setColor(Color.RED);  //0xffff0000
        paint.setAlpha(128); //透明度 0-255   ，float  0-1
        //空心
        //paint.setStyle(Paint.Style.STROKE);
        //线条加粗
        paint.setStrokeWidth(10);

        //设置渐变效果
        //Shader shader = new LinearGradient(0, 0, dm.widthPixels, dm.heightPixels, Color.RED, Color.BLUE, Shader.TileMode.MIRROR);
        //Shader shader = new RadialGradient(dm.widthPixels / 2, dm.heightPixels / 2, 400, Color.RED, Color.BLUE, Shader.TileMode.REPEAT);
        Shader shader = new SweepGradient(dm.widthPixels / 2, dm.heightPixels / 2, Color.RED, Color.BLUE);
        paint.setShader(shader);

        //圆心点
        point.set(getResources().getDisplayMetrics().widthPixels / 2, getResources().getDisplayMetrics().heightPixels / 2);

    }

    public MyView(Context context) {
        super(context);
        init();
    }


//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
////        MeasureSpec.AT_MOST;
//        //100 100;
//        //拼接大小
//        //  int width = MeasureSpec.makeMeasureSpec(100, MeasureSpec.AT_MOST);
//        super.onMeasure(width, width);
//    }

    Rect rect = new Rect(300, 500, 400, 800);
    RectF rectF = new RectF(200f, 0f, 400f, 200f);
    RectF rectF2 = new RectF(20f, 400f, 200f, 700f);

    //绘制方法
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制圆
        canvas.drawCircle(point.x, point.y, 100, paint);

        //点
        canvas.drawPoint(15, 15, paint);

        canvas.drawPoints(new float[]{30, 30, 40, 50, 60, 60, 70, 80}, paint);
        //线条
        canvas.drawLine(100, 50, 300, 300, paint);
        //矩形
        canvas.drawRect(rect, paint);

        //弧线
        canvas.drawArc(rectF, 45, 270, true, paint);

        //圆角矩形
        canvas.drawRoundRect(rectF2, 10, 10, paint);
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        //绘制文本
        canvas.drawText("我是绘制的文本", 10, 200, paint);

        canvas.drawText("我是居中的文字", getResources().getDisplayMetrics().widthPixels / 2, 300, paint);

        canvas.drawText("abcdefg", 0, paint.getTextSize(), paint);
    }


}
