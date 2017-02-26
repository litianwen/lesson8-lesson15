package com.example.administrator.lesson12_path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by Administrator on 2016/10/26.
 */

public class PathView extends View {

    Paint paint = new Paint();

    //path
    Path path = new Path();


    public PathView(Context context) {
        super(context);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        //path.addArc(new RectF(0, 0, 100, 100), 90, 100);


        path.moveTo(200, 200);
        path.lineTo(300, 300);
        path.quadTo(321, 42, 123, 424);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
        canvas.drawTextOnPath("跟着PATH路径行走的文字。。。", path, 0, 0, paint);
    }
}
