package com.example.administrator.filemanager.fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.filemanager.R;
import com.example.administrator.filemanager.adapter.FileGridAdapter;
import com.example.administrator.filemanager.utils.DpUtils;
import com.example.administrator.filemanager.utils.FileSizeUtils;
import com.example.administrator.mylib.adapter.ListItemAdapter;
import com.example.administrator.mylib.base.BaseFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/4.
 */

public class LocationFragment extends BaseFragment implements AdapterView.OnItemClickListener, View.OnClickListener {


    @BindView(R.id.tv_root)
    TextView tvRoot;
    @BindView(R.id.ll_menu)
    LinearLayout llMenu;
    @BindView(R.id.hsv)
    HorizontalScrollView hsv;
    @BindView(R.id.gv_content)
    GridView gvContent;

    List<File> mList = new ArrayList<>();
    //当前目录
    File dir = Environment.getExternalStorageDirectory();

    ListItemAdapter adapter;

    int type = FileSizeUtils.TYPE_NAME;
    boolean isAscending = true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = View.inflate(getContext(), R.layout.fragment_location, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvRoot.setTag(Environment.getExternalStorageDirectory().getAbsolutePath());
        tvRoot.setOnClickListener(this);
        adapter = new FileGridAdapter(getContext(), mList);
        gvContent.setAdapter(adapter);
        //加载数据
        initData();
        gvContent.setOnItemClickListener(this);
    }

    public void initData() {
        mList.clear();
        File[] files = dir.listFiles();
        if (files == null)
            return;
        for (File file : files) {
            mList.add(file);
        }
        mList = FileSizeUtils.sortByName(type, isAscending, mList);
        adapter.setList(mList);
    }

    @OnClick(R.id.tv_root)
    public void onClick() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mList.get(position).isDirectory()) {
            dir = mList.get(position);
            initData();
            //添加目录
            TextView tv = new TextView(getContext());
            tv.setText(dir.getName());
            tv.setTag(dir.getAbsolutePath());
            int padding = DpUtils.Dp2Px(getContext(), 10);
            tv.setPadding(padding, padding, padding, padding);
            tv.setOnClickListener(this);
            tv.setTextColor(Color.GRAY);
            tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.arrow_gray, 0);
            llMenu.addView(tv);
            //滑动到最右边
            //post 当显示在界面的时候
            tv.post(new Runnable() {
                @Override
                public void run() {
                    hsv.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                }
            });
        } else {
            //打开

        }
        //1. 切换目录
        //2. 显示导航目录
    }

    @Override
    public void onClick(View v) {
        String tag = v.getTag().toString();
        File file = new File(tag);
        dir = file;
        initData();
        for (int i = llMenu.getChildCount() - 1; i > 0; i--) {
            if (tag == llMenu.getChildAt(i).getTag()) {
                return;
            } else {
                llMenu.removeViewAt(i);
            }
        }
    }

    public void addNewFolder() {
        //给个名称
        final EditText et = new EditText(getContext());
        new AlertDialog.Builder(getContext()).setTitle("输入文件夹的名称")
                .setView(et)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = et.getText().toString();
                        if (TextUtils.isEmpty(name)) {
                            showToast("文件夹名称不能为空");
                        } else {
                            if (!new File(dir, name).mkdirs()) {
                                showToast("文件夹已存在");
                            } else {
                                showToast("创建成功");
                                initData();
                            }
                        }
                    }
                }).create().show();
    }

    List<File> search = new ArrayList<>();

    public void search(CharSequence s) {
        search.clear();
        //匹配当前文件 mList中是否包含S的所有值
        for (File file : mList) {
            if (file.getName().contains(s)) {
                search.add(file);
            }
        }
        adapter.setList(search);
    }
}
