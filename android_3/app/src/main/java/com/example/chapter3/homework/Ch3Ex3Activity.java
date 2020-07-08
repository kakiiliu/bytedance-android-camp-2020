package com.example.chapter3.homework;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用 ViewPager 和 Fragment 做一个简单版的好友列表界面
 * 1. 使用 ViewPager 和 Fragment 做个可滑动界面
 * 2. 使用 TabLayout 添加 Tab 支持
 * 3. 对于好友列表 Fragment，使用 Lottie 实现 Loading 效果，在 5s 后展示实际的列表，要求这里的动效是淡入淡出
 */
public class Ch3Ex3Activity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout mtabLayout;
    private ViewPager mviewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private List<String> mtitleList;
    private List<Fragment> mfragmentList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch3ex3);

        initView();

        // TODO: ex3-1. 添加 ViewPager 和 Fragment 做可滑动界面
        // TODO: ex3-2, 添加 TabLayout 支持 Tab

        mtabLayout = findViewById(R.id.tab_layout);
        mviewPager = findViewById((R.id.view_pager));
        mtabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (String title:mtitleList) {
            mtabLayout.addTab(mtabLayout.newTab().setText(title));
        }
        mtabLayout.setOnTabSelectedListener(this);
        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),mtitleList,mfragmentList);
        mviewPager.setAdapter(myViewPagerAdapter);
        mtabLayout.setupWithViewPager(mviewPager);
    }

    private void initView() {
        mtitleList = new ArrayList<>();
        mfragmentList = new ArrayList<>();
        mtitleList.add("Hello");
        mtitleList.add("ZJU");
        mtitleList.add("Friends");
        mfragmentList.add(new HelloFragment());
        mfragmentList.add(new ZJUFragment());
        mfragmentList.add(new FriendsFragment());
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mviewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
