package com.example.administrator.lesson10_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/10/19.
 */

/**
 * v4
 * 不适用viewpager就可以使用app包下的
 */
public class MyFragmentA extends Fragment {


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("TAG", "------------MyFragmentA----onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("TAG", "------------MyFragmentA----onCreate");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //去掉super
        Log.e("TAG", "-------------MyFragmentA---onCreateView");
        View layout = View.inflate(getActivity(), R.layout.frag_a, null);
        return layout;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("TAG", "------------MyFragmentA----onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("TAG", "------------MyFragmentA----onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("TAG", "-------------MyFragmentA---onResume");
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.e("TAG", "------------MyFragmentA----onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("TAG", "------------MyFragmentA----onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("TAG", "------------MyFragmentA----onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "------------MyFragmentA----onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("TAG", "------------MyFragmentA----onDetach");
    }
}
