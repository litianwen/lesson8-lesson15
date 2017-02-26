package com.example.administrator.lesson10_fragment4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/10/20.
 */

public class Frag extends Fragment {

    static Frag instance;

    public static Frag getInstance() {
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    EditText et;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(getActivity(), R.layout.fragment, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        et = (EditText) view.findViewById(R.id.et);
        view.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //要获取对方的值
                if (getId() == R.id.a) {
                    //获取B中的数据
                    Frag frag = (Frag) getActivity().getSupportFragmentManager().findFragmentById(R.id.b);
                    Toast.makeText(getActivity(), frag.getMsg(), Toast.LENGTH_LONG).show();
                } else {
                    Frag frag = (Frag) getActivity().getSupportFragmentManager().findFragmentById(R.id.a);
                    Toast.makeText(getActivity(), frag.getMsg(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public String getMsg() {
        return et.getText().toString();
    }
}
