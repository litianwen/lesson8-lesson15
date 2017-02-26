package com.example.administrator.imageselector;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Administrator on 2016/10/21.
 */

public class ShowBigImage extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView iv = new ImageView(this);
        File file = (File) getIntent().getSerializableExtra("file");
        iv.setImageURI(Uri.fromFile(file));
        setContentView(iv);
    }
}
