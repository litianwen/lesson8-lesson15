package com.example.administrator.lesson12_drawview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/10/26.
 */

public class ColorView extends View {

    //需要一张图片
    Bitmap bitmap;
    // int[] colors;

    public ColorView(Context context) {
        super(context);
        init();
    }

    public ColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.sepan);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("TAG", bitmap.getWidth() + "  " + bitmap.getHeight());
        super.onMeasure(MeasureSpec.makeMeasureSpec(bitmap.getWidth(), MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(bitmap.getHeight(), MeasureSpec.AT_MOST));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        Log.e("TAG", x + "   " + y);
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            int color = bitmap.getPixel((int) x, (int) y);
            setBackgroundColor(color);
            if (onColorSelectListener != null) {
                onColorSelectListener.onColorSelect(color);
            }
        }
        return true;
    }

    //回调
    public interface OnColorSelectListener {
        void onColorSelect(int color);
    }

    OnColorSelectListener onColorSelectListener;

    public void setOnColorSelectListener(OnColorSelectListener onColorSelectListener) {
        this.onColorSelectListener = onColorSelectListener;
    }
}
