package com.example.administrator.filemanager.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2016/11/3.
 */

public class ProgressView extends View {


    private int progress = 50;
    private int max = 100;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
        invalidate();
    }

    Paint paint = new Paint();
    RectF rect;

    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextAlign(Paint.Align.CENTER);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.rgb(0x33, 0x66, 0x99));
        paint.setStrokeWidth(1f);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - 3, paint);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - 10 - 3, paint);
        paint.setStrokeWidth(10);
        //  canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - 10, paint);
        float d = (float) progress / max * 360f;
        rect = new RectF(5 + 3, 5 + 3, getWidth() - 5 - 3, getHeight() - 5 - 3);
        canvas.drawArc(rect, -90, d, false, paint);
        //绘制文本
        paint.setStrokeWidth(1f);
        paint.setTextSize(20);
        paint.setColor(Color.BLACK);
        double ratio = (double) progress / max * 100;
        DecimalFormat format = new DecimalFormat("###.##");
        canvas.drawText(format.format(ratio) + "%", getWidth() / 2, getHeight() / 2 + paint.getTextSize() / 3, paint);
    }
}
