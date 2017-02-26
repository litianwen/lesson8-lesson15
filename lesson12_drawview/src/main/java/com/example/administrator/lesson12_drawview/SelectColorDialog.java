package com.example.administrator.lesson12_drawview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;

/**
 * Created by Administrator on 2016/10/26.
 */

public class SelectColorDialog extends Dialog {

    private final ColorView.OnColorSelectListener listener;

    public SelectColorDialog(Context context, @NonNull ColorView.OnColorSelectListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ColorView view = new ColorView(getContext());
        view.setOnColorSelectListener(listener);
        setContentView(view);
    }
}
