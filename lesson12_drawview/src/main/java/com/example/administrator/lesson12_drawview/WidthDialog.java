package com.example.administrator.lesson12_drawview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.SeekBar;

/**
 * Created by Administrator on 2016/10/26.
 */

public class WidthDialog extends Dialog {


    private final SeekBar.OnSeekBarChangeListener listener;

    public WidthDialog(Context context, @NonNull SeekBar.OnSeekBarChangeListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(getContext());
        SeekBar seekBar = new SeekBar(getContext());
        seekBar.setMax(9);//1-9
        seekBar.setOnSeekBarChangeListener(listener);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT);
        seekBar.setLayoutParams(params);
        layout.addView(seekBar);
        setContentView(layout);
    }
}
