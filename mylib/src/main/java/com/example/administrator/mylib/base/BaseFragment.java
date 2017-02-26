package com.example.administrator.mylib.base;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/4.
 */

public class BaseFragment extends Fragment {


    public void showToast(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void jumpActivity(Class cls) {
        startActivity(new Intent(getContext(), cls));
    }
}
