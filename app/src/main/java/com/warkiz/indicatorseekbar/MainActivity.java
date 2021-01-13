package com.warkiz.indicatorseekbar;

import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.widget.TextView;

import com.warkiz.indicatorseekbar.donation.BaseActivity;
import com.warkiz.indicatorseekbar.donation.DonationFragment;
import com.warkiz.indicatorseekbar.fragment.ContinuousFragment;
import com.warkiz.indicatorseekbar.fragment.CustomFragment;
import com.warkiz.indicatorseekbar.fragment.DiscreteFragment;
import com.warkiz.indicatorseekbar.fragment.IndicatorFragment;
import com.warkiz.indicatorseekbar.fragment.JavaBuildFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * created by zhuangguangquan on 2017/9/6
 */

public class MainActivity extends BaseActivity {

    private static String[] sType = new String[]{"continuous", "discrete", "custom", "java", "indicator", "donation"};
    private List<Fragment> mFragmentList = new ArrayList<>();

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initCreate() {
        super.initCreate();
        initFragment();
        initViews();
    }

    private void initFragment() {
        mFragmentList.add(new ContinuousFragment());
        mFragmentList.add(new DiscreteFragment());
        mFragmentList.add(new CustomFragment());
        mFragmentList.add(new JavaBuildFragment());
        mFragmentList.add(new IndicatorFragment());
        mFragmentList.add(new DonationFragment());
    }

    private void initViews() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        for (String s : sType) {
            TextView textView = new TextView(this);
            textView.setText(s);
            tabLayout.newTab().setCustomView(textView);
        }

    }

    private class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return sType.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return sType[position];
        }
    }


}
