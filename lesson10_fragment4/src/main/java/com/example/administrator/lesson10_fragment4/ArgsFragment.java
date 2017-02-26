package com.example.administrator.lesson10_fragment4;

/**
 * Created by Administrator on 2016/10/20.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 如果一个Fragment需要创建多个，并且有不同的属性
 */
public class ArgsFragment extends Fragment {

    class User {

    }

    String msg;


    public static ArgsFragment newInstance(String msg) {
        ArgsFragment fragment = new ArgsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("msg", msg);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        msg = getArguments().getString("msg");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        switch (gettype)
//        {
//            case 1:
//                return textView
//                        ;
//            case 2:
//                return image;
//        }
        return new TextView(getActivity());
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv = (TextView) view;
        tv.setText(msg);
    }
}
