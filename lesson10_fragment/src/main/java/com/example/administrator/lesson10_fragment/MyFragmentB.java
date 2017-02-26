package com.example.administrator.lesson10_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/19.
 */

public class MyFragmentB extends Fragment {
    List<String> mList;
    ArrayAdapter<String> adapter;
    ListView lv;

    /**
     * 初始化数据 与UI无关
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mList.add("项目 " + i);
        }
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View layout = View.inflate(getActivity(), R.layout.frag_b, null);
//        lv = (ListView) layout.findViewById(R.id.lv  );
        return View.inflate(getActivity(), R.layout.frag_b, null);
    }


    //加载数据

    /**
     * 与UI有关的
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv = (ListView) view.findViewById(R.id.lv);
        lv.setAdapter(adapter);
    }
}
