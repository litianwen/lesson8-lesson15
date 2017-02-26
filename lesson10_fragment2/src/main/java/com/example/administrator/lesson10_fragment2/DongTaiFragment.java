package com.example.administrator.lesson10_fragment2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/10/19.
 */

public class DongTaiFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EditText et = new EditText(getActivity());
        return et;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("TAG", "---------onActivityCreated");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("TAG", "--------onAttach");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("TAG", "----------onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("TAG", "----------onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("TAG", "----------onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "----------onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("TAG", "----------onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("TAG", "----------onDetach");
    }
}
