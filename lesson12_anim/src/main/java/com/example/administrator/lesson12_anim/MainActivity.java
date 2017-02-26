package com.example.administrator.lesson12_anim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.tv);
//        AnimationDrawable drawable = (AnimationDrawable) tv.getBackground();
//        drawable.start();
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim);
        // anim.setFillAfter(true);
//        tv.startAnimation(getScaleAnim());
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //开始
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //结束
                startActivity(new Intent(MainActivity.this, MyActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //重放

            }
        });
        tv.startAnimation(anim);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, MyActivity.class));
                //给动画
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });


    }


    public Animation getScaleAnim() {
//        new AlphaAnimation();
//        new TranslateAnimation()
        //  new RotateAnimation()
        Animation anim = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        anim.setDuration(3000);
        return anim;
    }


}
