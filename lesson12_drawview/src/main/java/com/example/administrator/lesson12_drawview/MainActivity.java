package com.example.administrator.lesson12_drawview;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import static com.example.administrator.lesson12_drawview.R.id.pop_tv_reform;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ColorView.OnColorSelectListener, SeekBar.OnSeekBarChangeListener {

    DrawView drawView;
    ImageView ivMenu;

    boolean isStart;
    boolean isReform;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivMenu = (ImageView) findViewById(R.id.iv_menu);
        drawView = (DrawView) findViewById(R.id.drawview);
        ivMenu.setOnClickListener(this);
        drawView.setOnDrawStateChangedListener(new DrawView.OnDrawStateChangedListener() {
            @Override
            public void startDraw() {
                isStart = true;
                //只要又绘制了一条
                isReform = false;
            }

            @Override
            public void clearDraw() {
                isStart = false;
            }

            @Override
            public void reform(boolean b) {
                isReform = b;
            }
        });
    }

    PopupWindow popupWindow;

    @Override
    public void onClick(View v) {

        //PopupWindow
        //1.布局
        View layout = View.inflate(this, R.layout.popup_layout, null);
        TextView tvColor = (TextView) layout.findViewById(R.id.pop_tv_color);
        TextView tvWidth = (TextView) layout.findViewById(R.id.pop_tv_width);
        TextView tvClear = (TextView) layout.findViewById(R.id.pop_tv_clear);
        TextView tvCa = (TextView) layout.findViewById(R.id.pop_tv_ca);
        TextView tvRepeal = (TextView) layout.findViewById(R.id.pop_tv_repeal);
        TextView tvReform = (TextView) layout.findViewById(pop_tv_reform);
        TextView tvSave = (TextView) layout.findViewById(R.id.pop_tv_save);
        tvRepeal.setEnabled(isStart);
        tvReform.setEnabled(isReform);
        tvSave.setEnabled(isStart);

        tvCa.setOnClickListener(popwindowClick);
        tvColor.setOnClickListener(popwindowClick);
        tvWidth.setOnClickListener(popwindowClick);
        tvClear.setOnClickListener(popwindowClick);
        tvRepeal.setOnClickListener(popwindowClick);
        tvReform.setOnClickListener(popwindowClick);
        tvSave.setOnClickListener(popwindowClick);

        popupWindow = new PopupWindow(layout);
        //2. 宽高getResources().getDisplayMetrics().widthPixels / 3
        popupWindow.setWidth(getResources().getDisplayMetrics().widthPixels / 3);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        //3. 焦点
        popupWindow.setFocusable(true);
        //4. 触摸到外部的时候，是否关闭他
        popupWindow.setOutsideTouchable(true);
        //5. 如果要执行上面一句， 1.设置上下文 2.给一个空背景
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //显示
        popupWindow.showAsDropDown(ivMenu, 0, 20);
    }

    //pop点击事件
    private View.OnClickListener popwindowClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pop_tv_color:
                    //getBaseContext;
//                    ColorDialog dialog = new ColorDialog(MainActivity.this);
//                    dialog.show();
                    SelectColorDialog dialog = new SelectColorDialog(MainActivity.this, MainActivity.this);
                    dialog.show();
                    break;
                case R.id.pop_tv_width:
                    WidthDialog dialog2 = new WidthDialog(MainActivity.this, MainActivity.this);
                    dialog2.show();
                    break;
                case R.id.pop_tv_clear:
                    drawView.clear();
                    break;
                case R.id.pop_tv_ca:
                    drawView.ca();
                    break;
                case R.id.pop_tv_repeal:
                    drawView.repeal();
                    break;
                case R.id.pop_tv_reform:
                    drawView.reform();
                    break;
                case R.id.pop_tv_save:
                    drawView.saveBitmap(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.png");
                    break;

            }
            //关闭pop
            popupWindow.dismiss();
        }
    };

    @Override
    public void onColorSelect(int color) {
        //选择了颜色
        drawView.setColor(color);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            drawView.setWidth(progress + 1);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
