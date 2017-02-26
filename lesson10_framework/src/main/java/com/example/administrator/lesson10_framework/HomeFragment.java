package com.example.administrator.lesson10_framework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/24.
 */

public class HomeFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(getActivity(), R.layout.fragment_home, null);
    }

    List<Fragment> mList = new ArrayList<>();

    RadioGroup home_rg;
    ViewPager home_vp;
    View view_indicator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList.add(new LvyouFragment());
        mList.add(new JingdianFragment());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        home_rg = (RadioGroup) view.findViewById(R.id.home_rg);
        home_vp = (ViewPager) view.findViewById(R.id.home_vp);
        view_indicator = view.findViewById(R.id.view_indicator);

        //在Fragment中设置FragmentPagerAdapter必须使用Child
        home_vp.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), mList));
        home_rg.setOnCheckedChangeListener(this);
        home_vp.addOnPageChangeListener(this);
        home_rg.check(R.id.home_rb_lvyou);
        initIndicator();
    }
    //将他做成 1半

    int width;

    public void initIndicator() {
        width = getResources().getDisplayMetrics().widthPixels / 2;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
        view_indicator.setLayoutParams(params);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        home_vp.setCurrentItem(checkedId == R.id.home_rb_lvyou ? 0 : 1);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.e("TAG", "页面:" + position + "      offset偏移百分比" + positionOffset + "     pix像素" + positionOffsetPixels);
        //拿到属性
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view_indicator.getLayoutParams();
        int left = (int) ((position + positionOffset) * width);
        params.setMargins(left, 0, 0, 0);
        view_indicator.setLayoutParams(params);
    }

    @Override
    public void onPageSelected(int position) {
        home_rg.check(position == 0 ? R.id.home_rb_lvyou : R.id.home_rb_jingdian);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
