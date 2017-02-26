package com.example.administrator.lesson10_backstatck;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/10/20.
 */

public class ContentFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(getActivity(), android.R.layout.simple_list_item_2, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
        TextView text2 = (TextView) view.findViewById(android.R.id.text2);
        Bundle bundle = getArguments();
        if (bundle == null) {
            text1.setText("测试");
            text2.setText("测试内容");
        } else {
            MainActivity.Article article = (MainActivity.Article) bundle.getSerializable("article");
            text1.setText(article.title);
            text2.setText(article.content);
        }


        //开始调用
        MainActivity activity = (MainActivity) getActivity();
        activity.showToast("这是Fragment调用Activity的方法");
    }

    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }
}
