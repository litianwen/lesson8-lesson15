package com.example.administrator.lesson12_drawview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */

public class ColorDialog extends Dialog {


    List<Integer> colors = new ArrayList<>();

    public ColorDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("颜色选择");
        super.onCreate(savedInstanceState);
        GridView gv = new GridView(getContext());
        gv.setNumColumns(20); //255*255*255
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                for (int k = 0; k < 16; k++) {
                    colors.add(Color.rgb(i * i, j * j, k * k));
                }
            }
        }
        gv.setHorizontalSpacing(10);
        gv.setVerticalSpacing(10);
        gv.setGravity(Gravity.CENTER);
        gv.setAdapter(adapter);
        setContentView(gv);
    }


    private BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return colors.size();
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
            if (convertView == null)
                convertView = new View(getContext());
            GridView.LayoutParams params = new GridView.LayoutParams(10, 10);
            convertView.setLayoutParams(params);
            convertView.setBackgroundColor(colors.get(position));
            return convertView;
        }
    };

}
