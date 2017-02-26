package com.example.administrator.lesson12_drawbitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/10/27.
 */

public class MyView extends View {

    Bitmap bmp;

    Paint paint = new Paint();


    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.c);
        src = new RectF(bmp.getWidth() / 2 - 50, bmp.getHeight() / 2 - 50, bmp.getWidth() / 2 + 50, bmp.getHeight() / 2 + 50);
        dst = new Rect(200, 200, 400, 400);

        paint.setAntiAlias(true);
        paint.setDither(true);
        Shader shaer = new BitmapShader(bmp, Shader.TileMode.MIRROR, Shader.TileMode.REPEAT);
        paint.setShader(shaer);
    }


    private RectF src = null;
    private Rect dst = null;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制Bitmap

        Matrix m = new Matrix();

        //每次set都会重置矩形
        m.setRotate(90, bmp.getWidth() / 2, bmp.getHeight() / 2);

        m.postTranslate(100, 100);

        m.preScale(0.5f, 0.5f, bmp.getWidth() / 2, bmp.getHeight() / 2);

        //错切
        m.postSkew(0.3f, 0.3f);

        // canvas.drawBitmap(bmp, m, null);

        // canvas.drawBitmap(bmp, src, dst, null);

        //拿view的高宽
        canvas.drawArc(src, 100, 270, true, paint);

    }
}
