package com.example.administrator.filemanager.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.example.administrator.filemanager.R;
import com.example.administrator.filemanager.adapter.CompressAdapter;
import com.example.administrator.filemanager.config.MyApp;
import com.example.administrator.mylib.base.BaseActivity;
import com.example.administrator.mylib.utils.FileUtils;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2016/11/4.
 */

public class CompressActivity extends BaseActivity {

    ListView lv;

    List<File> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compress);
        lv = (ListView) findViewById(R.id.lv);
        //数据
        initData();
        //显示
        //getBaseContext在Dialog，popupwindow等窗体
        lv.setAdapter(new CompressAdapter(getBaseContext(), mList));
    }

    private void initData() {
        String[] suffix = {".rar", ".zip", ".7z", ".jar", ".aar"};
        mList = FileUtils.getSuffixFile(MyApp.getInstance().getAllFiles(), suffix);
    }
}
