package com.example.administrator.filemanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.administrator.filemanager.R;
import com.example.administrator.filemanager.adapter.OtherAdapter;
import com.example.administrator.filemanager.adapter.OtherHistoryAdapter;
import com.example.administrator.filemanager.bean.HistoryBean;
import com.example.administrator.filemanager.config.MyApp;
import com.example.administrator.filemanager.db.HistoryDAO;
import com.example.administrator.mylib.base.BaseActivity;
import com.example.administrator.mylib.utils.FileUtils;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/4.
 */

public class OtherActivity extends BaseActivity implements AdapterView.OnItemClickListener {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_lately_open)
    TextView tvLatelyOpen;
    @BindView(R.id.tv_lately_create)
    TextView tvLatelyCreate;
    @BindView(R.id.tv_lately_down)
    TextView tvLatelyDown;
    @BindView(R.id.gridView)
    GridView gridView;

    List<File> mList;

    List<File> newList;

    List<HistoryBean> historyBeanList;

    OtherAdapter otherAdapter;

    OtherHistoryAdapter otherHistoryAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_other);
        //注解 0反射
        butterknife.ButterKnife.bind(this);
        initType();
        initData();
        gridView.setOnItemClickListener(this);
    }

    private void initData() {
        String[] sufix = null;
        switch (type) {
            case 1:
                sufix = new String[]{".png", ".jpg"};
                break;
            case 2:
                sufix = new String[]{".mp3", ".wav"};
                break;
            default:
                sufix = new String[]{".mp4", ".3gp"};
                break;
        }
        mList = FileUtils.getSuffixFile(MyApp.getInstance().getAllFiles(), sufix);
        otherAdapter = new OtherAdapter(this, mList, type);
        gridView.setAdapter(otherAdapter);
    }

    int type;

    private void initType() {
        //什么类型
        //1 2 3
        type = getIntent().getIntExtra("type", -1);
        if (type == -1)
            finish();
        switch (type) {
            case 1://图
                title.setText("图片");
                break;
            case 2://音
                title.setText("音乐");
                break;
            case 3://视频
                title.setText("视频");
                break;
        }
    }

    @OnClick({R.id.tv_lately_open, R.id.tv_lately_create, R.id.tv_all})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_lately_open:
                //点击最近打开
                点击最近打开();
                break;
            case R.id.tv_lately_create:
                newList = FileUtils.getLastFileByDay(mList, 3);
                otherAdapter.setList(newList);
                break;
            case R.id.tv_all:
                gridView.setNumColumns(4);
                gridView.setAdapter(otherAdapter);
                otherAdapter.setList(mList);
                gridView.setOnItemClickListener(this);
                break;
        }
    }

    private void 点击最近打开() {
        HistoryDAO dao = new HistoryDAO(this);
        historyBeanList = dao.selectByType(type);
        otherHistoryAdapter = new OtherHistoryAdapter(this, historyBeanList);
        gridView.setNumColumns(1);
        gridView.setAdapter(otherHistoryAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickItem(new File(historyBeanList.get(position).filePath));
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //File --> Music...
        //打开其他的文件
        //只要点击
        HistoryBean bean = new HistoryBean();
        bean.type = type;
        bean.lastTime = System.currentTimeMillis();
        bean.filePath = mList.get(position).getAbsolutePath();
        HistoryDAO dao = new HistoryDAO(this);
        dao.insert(bean);
        clickItem(mList.get(position));
    }

    private void clickItem(File file) {
        switch (type) {
            case 1:
                startActivity(new Intent(this, ImageActivity.class).putExtra("img", file));
                break;
        }
    }
}
