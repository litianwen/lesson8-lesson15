package com.example.administrator.filemanager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.filemanager.R;
import com.example.administrator.filemanager.adapter.GridAdapter;
import com.example.administrator.filemanager.config.MyApp;
import com.example.administrator.filemanager.ui.CompressActivity;
import com.example.administrator.filemanager.ui.OtherActivity;
import com.example.administrator.filemanager.utils.ConstantUtils;
import com.example.administrator.filemanager.view.ProgressView;
import com.example.administrator.mylib.base.BaseFragment;
import com.example.administrator.mylib.utils.FileUtils;

/**
 * Created by Administrator on 2016/11/3.
 */

public class HomeHomeFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    LinearLayout ll_internal, ll_sdcard;
    ProgressView pv_internal, pv_sdcard;
    TextView tv_internal_size, tv_sdcard_size;
    GridView gridView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //计算 sd卡 和 内部存储器

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(getContext(), R.layout.fragment_home_home, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        ll_internal = (LinearLayout) view.findViewById(R.id.ll_internal);
        ll_sdcard = (LinearLayout) view.findViewById(R.id.ll_sdcard);
        pv_internal = (ProgressView) view.findViewById(R.id.pv_internal);
        pv_internal.setMax((int) (FileUtils.getInternalSize() / 1024 / 1024));
        pv_internal.setProgress((int) ((FileUtils.getInternalSize() - FileUtils.getInternalAvailableSize()) / 1024 / 1024));
        pv_sdcard = (ProgressView) view.findViewById(R.id.pv_sdcard);
        tv_internal_size = (TextView) view.findViewById(R.id.tv_internal_size);
        tv_internal_size.setText((FileUtils.getInternalSize() - FileUtils.getInternalAvailableSize()) / 1024 / 1024 + "MB/" + FileUtils.getInternalSize() / 1024 / 1024 + "MB");
        tv_sdcard_size = (TextView) view.findViewById(R.id.tv_sdcard);
        tv_sdcard_size.setText((FileUtils.getSDCardSize() - FileUtils.getInternalAvailableSize()) / 1024 / 1024 + "MB/" + FileUtils.getSDCardSize() / 1024 / 1024 + "MB");
        pv_sdcard.setMax((int) (FileUtils.getSDCardSize() / 1024 / 1024));
        pv_sdcard.setProgress((int) ((FileUtils.getSDCardSize() - FileUtils.getInternalAvailableSize()) / 1024 / 1024));
        gridView = (GridView) view.findViewById(R.id.gridView);
        gridView.setAdapter(new GridAdapter(getContext(), ConstantUtils.gridTitle));
        gridView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (MyApp.getInstance().getAllFiles() == null) {
            showToast("正在扫描文件，请稍等");
            return;
        }
        switch (ConstantUtils.gridTitle[position]) {
            case "图片":
                image();
                break;
            case "音乐":
                music();
                break;
            case "视频":
                video();
                break;
            case "应用":
                apps();
                break;
            case "压缩文件":
                compress();
                break;
            case "下载":
                break;
            case "文档":
                break;
        }
    }

    private void image() {
        startActivity(new Intent(getContext(), OtherActivity.class).putExtra("type", 1));
    }

    private void video() {
        startActivity(new Intent(getContext(), OtherActivity.class).putExtra("type", 3));
    }

    private void music() {
        startActivity(new Intent(getContext(), OtherActivity.class).putExtra("type", 2));
    }

    private void apps() {
//        PackageManager manager = getActivity().getPackageManager();
//        List<PackageInfo> list = manager.getInstalledPackages(PackageManager.GET_ACTIVITIES);
//        for (PackageInfo packageInfo : list) {
//            Log.e("TAG", packageInfo.toString() + "   " + packageInfo.applicationInfo.className + "   " + packageInfo.applicationInfo.backupAgentName + "  " + packageInfo.applicationInfo.manageSpaceActivityName + "  " + packageInfo.applicationInfo.processName + "    " + packageInfo.applicationInfo.icon);
//        }
//        try {
//            Class cls = Class.forName(list.get(1).packageName + ".R");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        //tv_internal_size.setCompoundDrawablesWithIntrinsicBounds(0, 0, list.get(1).applicationInfo.icon, 0);
    }

    //启动压缩文件
    private void compress() {
        jumpActivity(CompressActivity.class);
    }
}
