package com.example.administrator.lesson15_camerademo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.administrator.mylib.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    GridView gv;
    List<File> fileList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gv = (GridView) findViewById(R.id.gv);
        initData();
    }

    private void initData() {
        new Thread() {
            @Override
            public void run() {
                fileList.clear();
                fileList.addAll(FileUtils.getAllPicture());
                //子线程操作UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }.start();

    }

    private BaseAdapter adapter = new BaseAdapter() {

        @Override
        public int getCount() {
            return fileList.size();
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
            int width = getResources().getDisplayMetrics().widthPixels / 3;
            GridView.LayoutParams params = new GridView.LayoutParams(width, width);
            switch (getItemViewType(position)) {
                case 0:
                    if (convertView == null || convertView instanceof ImageView) {
                        convertView = View.inflate(getBaseContext(), R.layout.camera_layout, null);
                        convertView.setLayoutParams(params);
                    }
                    MyCamera cam = (MyCamera) convertView.findViewById(R.id.cam);
                    break;
                case 1:
                    if (convertView == null || convertView instanceof MyCamera) {
                        convertView = new ImageView(MainActivity.this);
                    }
                    ImageView iv = (ImageView) convertView;
                    File file = fileList.get(position);
                    iv.setImageURI(Uri.fromFile(file));
                    convertView.setLayoutParams(params);
                    break;
            }
            return convertView;
        }

        @Override
        public int getItemViewType(int position) {
            return position == 0 ? 0 : 1;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }
    };
}
