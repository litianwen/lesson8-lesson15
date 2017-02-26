package com.example.administrator.filemanager.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.filemanager.R;
import com.example.administrator.mylib.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/3.
 */

public class HomeFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener, View.OnClickListener {


    RadioGroup tab_rg;
    RadioButton tab_rb_home, tab_rb_location;
    ViewPager home_vp;
    List<Fragment> fragments = new ArrayList<>();
    TextView tab1, tab2, tab3, tab4, tab5;

    LocationFragment locationFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(getContext(), R.layout.fragment_home, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        tab1 = (TextView) view.findViewById(R.id.tab1);
        tab1.setOnClickListener(this);
        tab2 = (TextView) view.findViewById(R.id.tab2);
        tab2.setOnClickListener(this);
        tab3 = (TextView) view.findViewById(R.id.tab3);
        tab4 = (TextView) view.findViewById(R.id.tab4);
        tab5 = (TextView) view.findViewById(R.id.tab5);
        tab_rg = (RadioGroup) view.findViewById(R.id.tab_rg);
        tab_rb_home = (RadioButton) view.findViewById(R.id.tab_rb_home);
        tab_rb_location = (RadioButton) view.findViewById(R.id.tab_rb_location);
        tab_rg.setOnCheckedChangeListener(this);
        tab_rg.check(R.id.tab_rb_home);
        fragments.add(new HomeHomeFragment());
        locationFragment = new LocationFragment();
        fragments.add(locationFragment);
        home_vp = (ViewPager) view.findViewById(R.id.home_vp);
        home_vp.setAdapter(new MyPagerAdapter(getChildFragmentManager(), fragments));
        home_vp.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //如果哪个被点击，另外一个 隐藏text
        if (checkedId == R.id.tab_rb_home) {
            tab_rb_home.setText("主页");
            tab_rb_location.setText("");
            tab4.setText("窗口");
            tab4.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.toolbar_windows, 0, 0);
            tab5.setText("历史记录");
            tab5.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.toolbar_history, 0, 0);
        } else {
            tab_rb_home.setText("");
            tab_rb_location.setText("本地");
            tab4.setText("视图");
            tab5.setText("更多");
            tab4.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.toolbar_view, 0, 0);
            tab5.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.toolbar_more, 0, 0);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tab_rg.check(position == 0 ? R.id.tab_rb_home : R.id.tab_rb_location);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        //判断是否是第二页
        if (home_vp.getCurrentItem() == 0)
            return;
        switch (v.getId()) {
            case R.id.tab1:
                locationFragment.addNewFolder();
                break;
            case R.id.tab2:
                showSearch();
                break;
        }
    }

    private void showSearch() {
        EditText et = new EditText(getContext());
        et.setBackgroundColor(Color.rgb(0x33, 0x66, 0x99));
        et.setPadding(5, 5, 5, 5);
        et.setTextSize(12);
        PopupWindow window = new PopupWindow(et, getResources().getDisplayMetrics().widthPixels, tab_rg.getHeight(), true);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(true);
        window.showAsDropDown(tab_rg, 0, -tab_rg.getHeight());
        et.startAnimation(getAnim());
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //立马去搜索
                locationFragment.search(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public Animation getAnim() {
        Animation anim = new TranslateAnimation(0, 0, -tab_rg.getHeight(), 0);
        anim.setDuration(2000);
        return anim;
    }


}
