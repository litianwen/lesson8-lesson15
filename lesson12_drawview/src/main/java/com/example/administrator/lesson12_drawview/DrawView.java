package com.example.administrator.lesson12_drawview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */

public class DrawView extends View {

    Paint paint = new Paint();

    int backgroundColor = Color.WHITE;

    Path path;

    //绘制到一张图片上
    Bitmap cacheBitmap;
    Canvas cacheCanvas;

    boolean isCa = false;

    Paint caPaint = new Paint();

    public DrawView(Context context) {
        super(context);
        init();
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        path = new Path();
        caPaint.setColor(backgroundColor);
        caPaint.setStrokeWidth(20);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //第一次测量的时候 没有高宽
        if (getWidth() != 0 && getHeight() != 0)
            initBitmap();
    }

    public void initBitmap() {
        cacheBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.RGB_565);
        //使用他绘制的 你看不到，绘制到了图片里面
        cacheCanvas = new Canvas(cacheBitmap);
        cacheCanvas.drawColor(backgroundColor);
    }

    //canvas是指屏幕的
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //清屏
        canvas.drawColor(backgroundColor);
        //绘制以前的线条
        canvas.drawBitmap(cacheBitmap, 0, 0, paint);
        if (isCa)
            canvas.drawPath(path, caPaint);
        else
            canvas.drawPath(path, paint);
    }


    //调整颜色
    public void setColor(int color) {
        paint.setColor(color);
    }

    public void setWidth(int width) {
        paint.setStrokeWidth(width);
    }

    //清除
    public void clear() {
        cacheBitmap.recycle();
        initBitmap();
        invalidate();
        if (onDrawStateChangedListener != null)
            onDrawStateChangedListener.clearDraw();
    }

    public void ca() {
        isCa = !isCa;
    }

    public void repeal() {
        //实际将Bitmap清空，上一次的所有Path重新绘制一次，刷新视图
        cacheCanvas.drawColor(backgroundColor);
        if (lastPaths.size() > 0) {
            removePaths.add(lastPaths.remove(lastPaths.size() - 1));
            int color = paint.getColor();
            float width = paint.getStrokeWidth();
            for (LastPath lastPath : lastPaths) {
                paint.setColor(lastPath.color);
                paint.setStrokeWidth(lastPath.width);
                cacheCanvas.drawPath(lastPath.path, paint);
            }
            paint.setColor(color);
            paint.setStrokeWidth(width);
        }
        if (onDrawStateChangedListener != null)
            onDrawStateChangedListener.reform(true);

        invalidate();
    }

    public void reform() {
        int color = paint.getColor();
        float width = paint.getStrokeWidth();
        if (removePaths.size() > 0) {
            LastPath path = removePaths.remove(removePaths.size() - 1);
            paint.setColor(path.color);
            paint.setStrokeWidth(path.width);
            cacheCanvas.drawPath(path.path, paint);
            lastPaths.add(path);
        }
        paint.setColor(color);
        paint.setStrokeWidth(width);
        invalidate();
    }

    //保存可能很慢
    public void saveBitmap(final String path) {
        //开始保存
        new Thread() {
            @Override
            public void run() {
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(path);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                cacheBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                //讲这个path直接绘制到Bitmap
                if (isCa) {
                    cacheCanvas.drawPath(path, caPaint);
                    //重置
                    path.reset();
                } else {
                    cacheCanvas.drawPath(path, paint);
                    //重置
                    lastPaths.add(new LastPath(new Path(path), paint.getColor(), paint.getStrokeWidth()));
                    path.reset();
                }
                if (onDrawStateChangedListener != null)
                    onDrawStateChangedListener.startDraw();
                removePaths.clear();
                break;
        }
        invalidate();
        return true;
    }

    //返回上一次操作
    List<LastPath> lastPaths = new ArrayList<>();
    //重复上一次操作
    List<LastPath> removePaths = new ArrayList<>();


    class LastPath {
        //路径
        Path path;
        int color;
        float width;

        public LastPath(Path path, int color, float width) {
            this.path = path;
            this.color = color;
            this.width = width;
        }
    }


    public interface OnDrawStateChangedListener {

        void startDraw();

        void clearDraw();

        void reform(boolean b);
    }

    OnDrawStateChangedListener onDrawStateChangedListener;

    public void setOnDrawStateChangedListener(OnDrawStateChangedListener onDrawStateChangedListener) {
        this.onDrawStateChangedListener = onDrawStateChangedListener;
    }
}
