package com.example.administrator.filemanager.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.administrator.mylib.base.BaseActivity;

import java.io.File;

/**
 * Created by Administrator on 2016/11/4.
 */

public class ImageActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView sv = new ScrollView(this);
        ImageView iv = new ImageView(this);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        iv.setAdjustViewBounds(true);
        sv.addView(iv);
        setContentView(sv);
        File file = (File) getIntent().getSerializableExtra("img");
        iv.setImageURI(Uri.fromFile(file));
    }
}
