package com.example.administrator.lesson12_path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/10/26.
 */

public class QiuView extends View {

    Paint paint = new Paint();


    PointF point = new PointF();

    public QiuView(Context context) {
        super(context);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setDither(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(point.x, point.y, 50, paint);
    }


    //   触摸事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            point.set(event.getX(), event.getY());
            invalidate();
        }
        return true;
    }
}
