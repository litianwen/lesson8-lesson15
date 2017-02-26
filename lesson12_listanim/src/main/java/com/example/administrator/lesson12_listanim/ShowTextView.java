package com.example.administrator.lesson12_listanim;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/10/27.
 */

public class ShowTextView extends LinearLayout implements View.OnClickListener {

    TextView tvContent, tvMore;

    boolean isShow;

    public ShowTextView(Context context) {
        super(context);
        init();
    }


    //设置文本
    public void setText(String text) {
        tvContent.setText(text);
        initText();
    }

    /**
     * <TextView
     * android:id="@+id/tv_content"
     * android:layout_width="match_parent"
     * android:layout_height="wrap_content"
     * android:gravity="center"
     * android:maxLines="5"
     * android:text="?
     */

    private void init() {
        //有两个组件
        setOrientation(VERTICAL);
        //marge标签
        View.inflate(getContext(), R.layout.textview_layout, this);
        //this.addView(layout);
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvContent.setPadding(10, 10, 10, 10);
        tvMore = (TextView) findViewById(R.id.tv_more);
        //开始设置
        initText();
    }

    private void initText() {
        //延迟的去操作view
        tvContent.post(new Runnable() {
            @Override
            public void run() {
                //判断是否单行
                Log.e("TAG", "------------" + tvContent.getLineCount());
                if (tvContent.getLineCount() != 1) {
                    tvContent.setGravity(Gravity.LEFT);
                } else {
                    tvContent.setGravity(Gravity.CENTER);

                }
                //判断是否超过5行
                if (tvContent.getLineCount() > 5) {
                    tvMore.setVisibility(View.VISIBLE);
                    tvMore.setOnClickListener(ShowTextView.this);
                } else {
                    tvMore.setVisibility(View.GONE);
                }
            }
        });
    }

    public ShowTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        //当用户点击的时候
        isShow = !isShow;
        if (isShow) {
            //需要显示更多
            tvMore.setText("收起");
            //属性动画
            //。。。代表可变长度数值
            Log.e("TAG", tvContent.getMaxLines() + "   " + tvContent.getLineCount());
            ObjectAnimator.ofInt(tvContent, "lines", 5, tvContent.getLineCount()).setDuration(500).start();
        } else {
            //收起
            tvMore.setText("显示更多");
            ObjectAnimator.ofInt(tvContent, "lines", tvContent.getLineCount(), 5).setDuration(500).start();
        }

    }
}
