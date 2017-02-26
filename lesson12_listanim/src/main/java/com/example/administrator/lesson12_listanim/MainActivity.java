package com.example.administrator.lesson12_listanim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;

    //自定义属性


    List<String> mList = new ArrayList<>();
    ShowTextView showTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < 10; i++) {
            mList.add("菜单" + i);
        }
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(adapter);
        showTextView = (ShowTextView) findViewById(R.id.showTextView);
        showTextView.setText("加载动画\n" +
                "AnimationUtils\n" +
                "\n" +
                "所有动画都有的属性\n" +
                "fillAfter  动画播放完毕后 停留在最后的动画的状态,不能同时播放帧动画后停止状态\n" +
                "android:repeatCount=\"infinite\"  次数，可选数值\n" +
                "android:repeatMode=\"reverse\" 反转    模式 默认重复，\n" +
                "android:interpolator=\"@android:anim/bounce_interpolator\" 弹球效果，不能在set里面\n" +
                "\n" +
                "java代码创建\n" +
                " Animation anim = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);\n" +
                " \n" +
                "如果没有写TO_SELF,那么针对父窗体\n" +
                "\n" +
                "增对activity的进出场动画\n" +
                "overridePendingTransition\n" +
                "必须放在finish或者startactivity还有forResult之后的一句，");
    }


    private BaseAdapter adapter = new BaseAdapter() {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //每条都给动画
            if (convertView == null)
                convertView = View.inflate(MainActivity.this, android.R.layout.simple_list_item_1, null);
            TextView tv = (TextView) convertView;
            tv.setText(mList.get(position));
            //动画
            tv.startAnimation(getTranAnim(position));
            return convertView;
        }


        public Animation getTranAnim(int position) {
            Animation anim = new TranslateAnimation(getResources().getDisplayMetrics().widthPixels, 0, 0, 0);
            anim.setDuration(300);
            anim.setStartOffset(100 * position);
            return anim;
        }
    };

}
