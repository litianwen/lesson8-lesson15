package com.example.administrator.popupwindowdemo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    List<String> numbers = new ArrayList<>();
    ImageView iv_down;
    EditText et_content;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 50; i++) {
            numbers.add("13815474" + i);
        }
        iv_down = (ImageView) findViewById(R.id.iv_down);
        et_content = (EditText) findViewById(R.id.et_content);
        rl = (RelativeLayout) findViewById(R.id.rlS);
        iv_down.setOnClickListener(this);
    }

    PopupWindow window;

    @Override
    public void onClick(View v) {
        ListView lv = new ListView(this);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                et_content.setText(numbers.get(position));
                window.dismiss();
            }
        });
        lv.setAdapter(adapter);
        window = new PopupWindow(lv, rl.getWidth(), 200, true);
        window.setOutsideTouchable(true);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.showAsDropDown(rl, 0, 50);
    }

    private BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return numbers.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHodler holder;
            if (convertView == null) {
                convertView = View.inflate(getBaseContext(), R.layout.iten_layout, null);
                holder = new ViewHodler(convertView);
                convertView.setTag(holder);
            } else
                holder = (ViewHodler) convertView.getTag();
            holder.tv_phone.setText(numbers.get(position));
            holder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numbers.remove(position);
                    adapter.notifyDataSetChanged();
                }
            });
//            holder.tv_phone.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    et_content.setText(numbers.get(position));
//                    window.dismiss();
//                }
//            });
            return convertView;
        }

        class ViewHodler {
            TextView tv_phone;
            ImageView iv_delete;

            public ViewHodler(View convertView) {
                tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
                iv_delete = (ImageView) convertView.findViewById(R.id.iv_delete);
            }
        }
    };
}
